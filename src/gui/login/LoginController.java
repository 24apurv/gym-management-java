/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import com.db.DatabaseConnection;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.persistence.PersistUsers;
import gui.splashScreen.TitanX;
import gui.user.UserWindowController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void login(MouseEvent event) throws IOException {
        String userName = username.getText();
        String pwd = password.getText();
        try {
            PersistUsers.retrieve(userName, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String privilege = PersistUsers.getUsers().getPrivilege();
        if(new String("user").equals(privilege))
        {
            Stage stage = TitanX.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/gui/user/UserWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        else if(new String("admin").equals(privilege))
        {
            Stage stage = TitanX.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/gui/admin/AdminWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        else
        {
            
        }
    }

    @FXML
    private void close(MouseEvent event) throws SQLException {
        DatabaseConnection.getConnection().close();
        System.exit(0);
    }
    
}
