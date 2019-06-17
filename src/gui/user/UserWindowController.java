
package gui.user;

import com.db.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.model.Customer;
import com.persistence.PersistCustomer;
import com.persistence.PersistUsers;
import gui.splashScreen.TitanX;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class UserWindowController implements Initializable {

    @FXML
    private BorderPane parent;
    @FXML
    private Label lblHello;
    @FXML
    private StackPane stackPane;
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, String> col_Name;
    @FXML
    private TableColumn<Customer, String> col_MobileNumber;
    @FXML
    private TableColumn<Customer, String> col_StartingDate;
    @FXML
    private TableColumn<Customer, String> col_ExpirationDate;
    @FXML
    private TableColumn<Customer, String> col_Branch;
    @FXML
    private TableColumn<Customer, String> col_PRN;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnRefresh;
    @FXML
    private JFXButton btnPayment;

    ObservableList<Customer> list;
    
    private static String prn;
    
     public static String getPrn()
    {
        return prn;
    }
    @FXML
    private JFXButton btnPaymentRecord;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String name = PersistUsers.getUsers().getUsersname();
        lblHello.setText("Hello, "+name);
        try {
           loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(UserWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void close(MouseEvent event) throws SQLException {
        DatabaseConnection.getConnection().close();
        System.exit(0);
    }

    @FXML
    private void maximize(MouseEvent event) {
        Stage stage = TitanX.stage;
        stage.setFullScreen(true);
    }

    @FXML
    private void search(KeyEvent event) throws SQLException {
        String searchString=txtSearch.getText();
        if(searchString.matches("[0-9]+"))
        {
            searchString=searchString+"%";
            list=PersistCustomer.searchByMobile(searchString);
            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_MobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
            col_StartingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
            col_ExpirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
            col_Branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
            col_PRN.setCellValueFactory(new PropertyValueFactory<>("prn"));
            table.setItems(list);
        }
        else
        {
            searchString="%"+searchString+"%";
            list=PersistCustomer.searchByName(searchString);
            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_MobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
            col_StartingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
            col_ExpirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
            col_Branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
            col_PRN.setCellValueFactory(new PropertyValueFactory<>("prn"));
            table.setItems(list);
        }
    }

    @FXML
    private void addCustomer(MouseEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void update(MouseEvent event) throws IOException, SQLException {
        Customer customer=table.getSelectionModel().getSelectedItem();
        
        if(customer==null)
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
        prn=customer.getPrn().toString();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateInformation.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        loadTable();
    }

    @FXML
    private void delete(MouseEvent event) {
        Customer customer=table.getSelectionModel().getSelectedItem();
        
        if(customer==null)
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
        prn=customer.getPrn().toString();
        
        String title="Delete";
        String content="Are you sure you want to delete customer record?";
        JFXDialogLayout dialogContent=new JFXDialogLayout();
        dialogContent.setHeading(new Text(title));
        dialogContent.setBody(new Text(content));
        JFXButton yes=new JFXButton("Yes");
        yes.setButtonType(JFXButton.ButtonType.RAISED);
        JFXButton no=new JFXButton("No");
        no.setButtonType(JFXButton.ButtonType.RAISED);
        dialogContent.setActions(yes,no);
        JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
        yes.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            try {
                PersistCustomer.remove(customer.getPrn());
            } catch (SQLException ex) {
                Logger.getLogger(UserWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
                dialog.close();
            try {
                loadTable();
            } catch (SQLException ex) {
                Logger.getLogger(UserWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        no.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            dialog.close();
            try {
                loadTable();
            } catch (SQLException ex) {
                Logger.getLogger(UserWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        dialog.show();
    }

    @FXML
    private void refresh(MouseEvent event) throws SQLException {
        loadTable();
    }

    @FXML
    private void payment(MouseEvent event) throws IOException {
        Customer customer=table.getSelectionModel().getSelectedItem();
        
        if(customer==null)
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
        prn=customer.getPrn().toString();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
    
    private void loadTable() throws SQLException
    {
        list = PersistCustomer.retrieveAll();
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_MobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        col_StartingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        col_ExpirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        col_Branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        col_PRN.setCellValueFactory(new PropertyValueFactory<>("prn"));
        table.setItems(list);
    }

    @FXML
    private void paymentRecords(MouseEvent event) throws IOException {
        Customer customer=table.getSelectionModel().getSelectedItem();
        
        if(customer==null)
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
        prn=customer.getPrn().toString();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PaymentRecords.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
    
}
