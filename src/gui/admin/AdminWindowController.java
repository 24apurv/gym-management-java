
package gui.admin;

import com.jfoenix.controls.JFXButton;
import com.model.Branch;
import com.model.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class AdminWindowController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void exitApplication(MouseEvent event) {
    }

    @FXML
    private void addBranch(MouseEvent event) {
    }

    @FXML
    private void removeBranch(MouseEvent event) {
    }

    @FXML
    private void editBranch(MouseEvent event) {
    }

    @FXML
    private void addUser(MouseEvent event) {
    }

    @FXML
    private void removeUser(MouseEvent event) {
    }

    @FXML
    private void updateUser(MouseEvent event) {
    }
    
}
