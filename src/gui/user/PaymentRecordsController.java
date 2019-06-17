
package gui.user;

import com.db.DatabaseConnection;
import com.invoice.Invoice;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.model.Transaction;
import com.persistence.PersistCurrentTransaction;
import com.persistence.PersistTransaction;
import gui.user.UserWindowController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;


public class PaymentRecordsController implements Initializable {

    @FXML
    private TableView<Transaction> paymentRecords;
    
    ObservableList<Transaction> list;
    @FXML
    private StackPane stackPane;
    @FXML
    private TableColumn<Transaction, String> col_date;
    @FXML
    private TableColumn<Transaction, String> col_membershipPlan;
    @FXML
    private TableColumn<Transaction, String> col_amount;
    @FXML
    private TableColumn<Transaction, String> col_startingDate;
    @FXML
    private TableColumn<Transaction, String> col_invoiceNumber;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        list = PersistTransaction.retrieve(UserWindowController.getPrn());
        col_date.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        col_membershipPlan.setCellValueFactory(new PropertyValueFactory<>("membershipPlan"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_startingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        col_invoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        paymentRecords.setItems(list);
        } 
        catch (SQLException ex) {
            Logger.getLogger(PaymentRecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void generateInvoice(MouseEvent event) throws SQLException, JRException, ClassNotFoundException, IOException {
        Transaction transaction=paymentRecords.getSelectionModel().getSelectedItem();
        if(transaction==null)
        {
            String title="Warning";
            String content="No record selected";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            return;
        }
        PersistCurrentTransaction.store(transaction);
        Invoice.generateInvoice();
        PersistCurrentTransaction.remove();
    }

    @FXML
    private void closeWindow(MouseEvent event) throws SQLException {
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }
    
}
