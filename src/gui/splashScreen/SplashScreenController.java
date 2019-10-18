
package gui.splashScreen;

import com.db.DatabaseConnection;
import com.itextpdf.text.log.SysoLogger;
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
import javafx.scene.layout.AnchorPane;


public class SplashScreenController implements Initializable {
        
    
    @FXML
    private AnchorPane parent;
    private boolean flag;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DatabaseConnection.makeConnection();
            flag=true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Parent fxml;
            try {
                fxml = FXMLLoader.load(getClass().getResource("/gui/error/InternetError.fxml"));
            
            parent.getChildren().removeAll();
            parent.getChildren().setAll(fxml);
            flag=false;
            } catch (IOException ex1) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        if(flag){
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/gui/login/Login.fxml"));
            parent.getChildren().removeAll();
            parent.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }    
    
}
