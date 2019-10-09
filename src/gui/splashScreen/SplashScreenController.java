
package gui.splashScreen;

import com.db.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;


public class SplashScreenController implements Initializable {
        
    
    @FXML
    private AnchorPane parent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnection.makeConnection();
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/gui/login/Login.fxml"));
            parent.getChildren().removeAll();
            parent.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
