
package gui.admin;

import com.db.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.model.Branch;
import com.model.Users;
import com.persistence.PersistBranch;
import com.persistence.PersistUsers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminWindowController implements Initializable {

    private StackPane stackPane;
    @FXML
    private BorderPane parent;
    @FXML
    private Label lblHello;
    @FXML
    private TableColumn<Branch, String> col_BranchName;
    @FXML
    private TableColumn<Branch, String> col_BranchCode;
    @FXML
    private TableColumn<Branch, String> col_Address;
    @FXML
    private JFXButton btnAddBranch;
    @FXML
    private JFXButton btnRemoveBranch;
    @FXML
    private JFXButton btnEditBranch;
    @FXML
    private TableColumn<Users, String> col_UsersName;
    @FXML
    private TableColumn<Users, String> col_Username;
    @FXML
    private TableColumn<Users, String> col_Branch;
    @FXML
    private TableColumn<Users, String> col_Privilege;
    @FXML
    private JFXButton btnAddUser;
    @FXML
    private JFXButton btnRemoveUser;
    @FXML
    private JFXButton btnUpdateUser;
    
    ObservableList<Branch> branchList;
    
    ObservableList<Users> usersList;
    @FXML
    private TableView<Branch> branchTable;
    @FXML
    private TableView<Users> usersTable;
    
    private static Branch branch;
    
    private static Users user;
    
    public static Branch getBranch()
    {
        return branch;
    }
    
    public static Users getUser()
    {
        return user;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lblHello.setText("Hello, "+PersistUsers.getUsers().getUsername());
            branchList = PersistBranch.retrieveAll();
            usersList = PersistUsers.retrieveAll();
            
            col_BranchName.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_BranchCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            col_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
            branchTable.setItems(branchList);
            
            col_UsersName.setCellValueFactory(new PropertyValueFactory<>("usersname"));
            col_Username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_Branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
            col_Privilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));;
            usersTable.setItems(usersList);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void exitApplication(MouseEvent event) throws SQLException {
        DatabaseConnection.getConnection().close();
        System.exit(0);
    }

    @FXML
    private void addBranch(MouseEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddBranch.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void removeBranch(MouseEvent event) {
    }

    @FXML
    private void editBranch(MouseEvent event) throws IOException {
        branch = branchTable.getSelectionModel().getSelectedItem();
        if(branch==null)
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
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateBranch.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addUser(MouseEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void removeUser(MouseEvent event) {
    }

    @FXML
    private void updateUser(MouseEvent event) throws IOException {
        user = usersTable.getSelectionModel().getSelectedItem();
        if(user==null)
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
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateUser.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
    
}
