
package gui.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.model.Customer;
import com.persistence.PersistBranch;
import com.persistence.PersistCustomer;
import com.persistence.PersistUsers;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmayee
 */
public class AddCustomerController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField emailId;
    @FXML
    private JFXTextField mobileNumber;
    @FXML
    private JFXTextArea address;
    @FXML
    private JFXComboBox<String> branch;
    @FXML
    private JFXRadioButton male;
    @FXML
    private ToggleGroup group;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXRadioButton non_binary;
    @FXML
    private JFXDatePicker dateOfBirth;
    @FXML
    private AnchorPane parent;
    @FXML
    private JFXButton btnSubmit;
    
    ObservableList<String> list;
    @FXML
    private Label errorLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            list = PersistBranch.retrieveAllNames();
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        branch.setItems(list);
        branch.setValue(PersistUsers.getUsers().getBranch());
        mobileNumber.setText("+91-");
    }    
    @FXML
    private void submit(MouseEvent event) throws SQLException {
        
        if(name.getText()==null || dateOfBirth.getValue()==null || address.getText()==null || branch.getValue()==null || group.getSelectedToggle()==null)
        {
            errorLbl.setText("Fill all the fields!");
            return;
        }
        
        if(!emailId.getText().matches("^(.+)@(.+)$"))
        {
            errorLbl.setText("Invalid email!");
            emailId.clear();
            return;
        }
        if( !mobileNumber.getText().matches("^(\\+91[-s]?)?[0]?(91)?[789]\\d{9}$"))
        {
            errorLbl.setText("Invalid mobile number!");
            mobileNumber.clear();
            return;
        }
        
        Customer customer = new Customer();
        customer.setName(name.getText());
        Date date=Date.valueOf(dateOfBirth.getValue());
        customer.setDateOfBirth(date);
        customer.setAddress(address.getText());
        customer.setMobileNumber(mobileNumber.getText());
        customer.setEmailId(emailId.getText());
        customer.setBranch(branch.getValue());
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
        PersistCustomer.add(customer);
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitWindow(MouseEvent event) {
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }
    
}
