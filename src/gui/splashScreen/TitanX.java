
package gui.splashScreen;

import com.db.DatabaseConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class TitanX extends Application {
    
    public static Stage stage = null;
    private boolean flag;
    
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        
        stage.setScene(scene);
        stage.show();
        
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), root);
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        
        fadeIn.play();
        
        fadeIn.setOnFinished((e) -> {
               try {
            DatabaseConnection.makeConnection();
            flag=true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Parent fxml;
            try {
                fxml = FXMLLoader.load(getClass().getResource("/gui/error/InternetError.fxml"));
            
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
            flag=false;
            } catch (IOException ex1) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
               if(flag){
            try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/gui/login/Login.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.stage=stage;
               }
        });

     
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
