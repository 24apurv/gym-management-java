
package gui.user;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.model.Customer;
import com.persistence.PersistBranch;
import com.persistence.PersistCustomer;
import com.persistence.PersistMembershipPlans;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateInformationController implements Initializable {

    @FXML
    private ToggleGroup group;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXDatePicker dateOfBirth;
    @FXML
    private JFXTextField emailId;
    @FXML
    private JFXTextField mobileNumber;
    @FXML
    private JFXTextArea address;
    @FXML
    private JFXComboBox<String> branch;
    @FXML
    private JFXComboBox<String> membershipPlan;
    @FXML
    private JFXDatePicker startingDate;
    @FXML
    private JFXDatePicker expirationDate;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXRadioButton non_binary;

    Customer customer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        
        customer = PersistCustomer.retrieve(UserWindowController.getPrn());
        name.setText(customer.getName());
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate=LocalDate.parse(customer.getDateOfBirth().toString());
        dateOfBirth.setValue(localDate);    
        emailId.setText(customer.getEmailId());
        mobileNumber.setText(customer.getMobileNumber());
        address.setText(customer.getAddress());
        localDate=LocalDate.parse(customer.getStartingDate().toString());
        startingDate.setValue(localDate);
        localDate=LocalDate.parse(customer.getExpirationDate().toString());
        expirationDate.setValue(localDate);
        String gender=customer.getGender();
        if("M".equals(gender))
        {
            group.selectToggle(male);
        }
        else if("F".equals(gender))
        {
           group.selectToggle(female);
        }
        else
        {
           group.selectToggle(non_binary);
        }
            ObservableList<String> branchList = PersistBranch.retrieveAllNames();
            branch.setItems(branchList);
            branch.setValue(customer.getBranch());
            
            ObservableList<String> planList = PersistMembershipPlans.retrieveAllNames();
            membershipPlan.setItems(planList);
            membershipPlan.setValue(customer.getMembershipPlan());
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }    


    @FXML
    private void update(MouseEvent event) throws SQLException {
        
        customer.setName(name.getText());
        Date date=Date.valueOf(dateOfBirth.getValue());
        customer.setDateOfBirth(date);
        customer.setAddress(address.getText());
        customer.setMobileNumber(mobileNumber.getText());
        customer.setEmailId(emailId.getText());
        date=Date.valueOf(startingDate.getValue());
        customer.setStartingDate(date);
        date=Date.valueOf(expirationDate.getValue());
        customer.setExpirationDate(date);
        customer.setBranch(branch.getValue());
        customer.setMembershipPlan(membershipPlan.getValue());
        Toggle toggle=group.getSelectedToggle();
        if(male.equals(toggle))
        {
            customer.setGender("M");
        }
        else if(female.equals(toggle))
        {
            customer.setGender("F");
        }
         else
        {
            customer.setGender("N");
        }
        customer.setPrn(Integer.parseInt(UserWindowController.getPrn()));
        PersistCustomer.update(customer);
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }
    
}
