
package gui.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.model.Users;
import com.persistence.PersistUsers;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UpdateUserController implements Initializable {

    private StackPane stackPane;
    
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXComboBox<String> branch;
    @FXML
    private JFXComboBox<String> privilege;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private JFXButton btnSubmit;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Users user = PersistUsers.retrieve(AdminWindowController.getUser().getUsername(), AdminWindowController.getUser().getPassword());
            username.setText(user.getUsername());
            name.setText(user.getUsersname());
            branch.setValue(user.getBranch());
            privilege.setValue(user.getPrivilege());
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void exitWindow(MouseEvent event) {
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void submit(MouseEvent event) throws SQLException {
        if(!(password.getText().equals(confirmPassword.getText())))
        {
            String title="Warning";
            String content="Passwords do not match";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            password.clear();
            confirmPassword.clear();
            return;
        }
        Users user = new Users();
        user.setUsersname(username.getText());
        user.setUsersname(name.getText());
        user.setPassword(password.getText());
        user.setBranch(branch.getValue());
        user.setPrivilege(privilege.getValue());
        PersistUsers.update(user);
        
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }
    
}
