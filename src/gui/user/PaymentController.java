
package gui.user;

import com.invoice.Invoice;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mail.Mail;
import com.model.Customer;
import com.model.MembershipPlans;
import com.model.Transaction;
import com.persistence.PersistCurrentTransaction;
import com.persistence.PersistCustomer;
import com.persistence.PersistMembershipPlans;
import com.persistence.PersistTransaction;
import com.persistence.PersistUsers;
import gui.user.UserWindowController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;


public class PaymentController implements Initializable {

    Transaction transaction;
    
    MembershipPlans membershipPlan;
    
    ObservableList<String> planList;
    ObservableList<String> paymentModes;
    
    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField mobileNumber;
    @FXML
    private JFXTextField branch;
    @FXML
    private JFXDatePicker startingDate;
    @FXML
    private JFXDatePicker expirationDate;
    @FXML
    private JFXComboBox<String> plan;
    @FXML
    private JFXTextField amount;
    @FXML
    private JFXComboBox<String> modeOfPayment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Customer customer = PersistCustomer.retrieve(UserWindowController.getPrn());
            name.setText(customer.getName());
            mobileNumber.setText(customer.getMobileNumber());
            branch.setText(customer.getBranch());
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            planList = PersistMembershipPlans.retrieveAllNames();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        plan.setItems(planList);
            
        paymentModes=FXCollections.observableArrayList();
        paymentModes.add("Cash");
        paymentModes.add("Cheque");
        paymentModes.add("Debit/Credit Card");
        modeOfPayment.setItems(paymentModes);
        
        startingDate.setValue(LocalDate.now());
        startingDate.setEditable(false);
    }    

    @FXML
    private void pay(MouseEvent event) throws SQLException, IOException {
        File file = new File("C:/Invoice/invoice.pdf");
        file.delete();
        recordTransaction();
        transaction = PersistTransaction.retrieveLatest();
        System.out.println(transaction);
        PersistCurrentTransaction.store(transaction);
        generateInvoice();
        PersistCurrentTransaction.remove();
        updateCustomerRecord();
        Customer customer = PersistCustomer.retrieve(UserWindowController.getPrn());
        Mail.sendMail(customer);
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

    @FXML
    private void setValue(ActionEvent event) throws SQLException {
        membershipPlan = PersistMembershipPlans.retrieve(plan.getValue());
        amount.setText(membershipPlan.getAmount().toString()+"0/-");
        startingDate.setEditable(true);
        setExpirationDate();
    }
    
    void recordTransaction() throws SQLException 
    {
        transaction = new Transaction();
        transaction.setBranch(branch.getText());
        transaction.setUsersname(PersistUsers.getUsers().getUsersname());
        transaction.setPrn(Integer.parseInt(UserWindowController.getPrn()));
        transaction.setCustomerName(name.getText());
        transaction.setMobileNumber(mobileNumber.getText());
        transaction.setMembershipPlan(plan.getValue());
        transaction.setStartingDate(Date.valueOf(startingDate.getValue()));
        transaction.setExpirationDate(Date.valueOf(expirationDate.getValue()));
        Double basicAmount = new Double(0);
        basicAmount = Double.parseDouble(membershipPlan.getAmount().toString());
        transaction.setAmount(basicAmount);
        transaction.setSgst(basicAmount*0.09f);
        transaction.setCgst(basicAmount*0.09f);
        transaction.setTotalAmount(basicAmount+(basicAmount*0.18f));
        transaction.setPaymentMode(modeOfPayment.getValue());
        PersistTransaction.store(transaction);
    }
    
    
    void generateInvoice() throws IOException
    {
        try {
            Invoice.generateInvoice();
        } catch (JRException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    void updateCustomerRecord() throws SQLException
    {
        Customer customer = PersistCustomer.retrieve(UserWindowController.getPrn());
        customer.setStartingDate(Date.valueOf(startingDate.getValue()));
        customer.setExpirationDate(Date.valueOf(expirationDate.getValue()));
        customer.setMembershipPlan(plan.getValue());
        PersistCustomer.membershipUpgrade(customer);
    }

    @FXML
    private void setExpiryDate(ActionEvent event) {
        setExpirationDate();
    }

    public void setExpirationDate() {
        expirationDate.setValue(startingDate.getValue().plusMonths(membershipPlan.getDuration()));
    }
    
    
    
}
