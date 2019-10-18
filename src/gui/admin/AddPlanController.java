
package gui.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.model.MembershipPlans;
import com.persistence.PersistMembershipPlans;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AddPlanController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField duration;
    @FXML
    private JFXTextField amount;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private FontAwesomeIcon close;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void submit(MouseEvent event) throws SQLException {
        MembershipPlans plan = new MembershipPlans();
        plan.setName(name.getText());
        plan.setAmount(Float.parseFloat(amount.getText()));
        plan.setDuration(Integer.parseInt(duration.getText()));
        PersistMembershipPlans.store(plan);
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitWindow(MouseEvent event) {
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }
    
}
