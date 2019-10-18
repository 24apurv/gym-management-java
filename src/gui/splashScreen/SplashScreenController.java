
package gui.splashScreen;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class SplashScreenController implements Initializable {
        
    
    @FXML
    private AnchorPane parent;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            start();
        } catch (InterruptedException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }  
    
    public void start() throws InterruptedException
    {
        
    }
    
}
    

