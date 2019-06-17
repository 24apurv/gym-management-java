
package gui.admin.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.model.Users;
import com.persistence.PersistUsers;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddUserController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtConfirmPassword;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXComboBox<String> branch;
    @FXML
    private JFXComboBox<String> privilege;
    @FXML
    private JFXButton btnSubmit;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void submit(MouseEvent event) throws SQLException {
        if(txtPassword.getText().equals(txtConfirmPassword.getText()))
        {
            Users users = new Users();
            users.setUsername(txtUsername.getText());
            users.setPassword(txtPassword.getText());
            users.setUsersname(txtName.getText());
            users.setBranch(branch.getValue());
            users.setPrivilege(privilege.getValue());
            PersistUsers.store(users);
            Node source=(Node)event.getSource();
            Stage stage=(Stage)source.getScene().getWindow();
            stage.close();
        }
        
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }
    
}
