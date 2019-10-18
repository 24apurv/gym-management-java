
package gui.admin;

import com.jfoenix.controls.JFXButton;
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


public class UpdatePlanController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXTextField duration;
    @FXML
    private JFXTextField amount;
    @FXML
    private FontAwesomeIcon close;
    
    private MembershipPlans plan; 


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        plan = AdminWindowController.getPlan();
        name.setText(plan.getName());
        duration.setText(plan.getDuration().toString());
        amount.setText(plan.getAmount().toString());
    }    

    @FXML
    private void submit(MouseEvent event) throws SQLException {
        plan.setDuration(Integer.parseInt(duration.getText()));
        plan.setAmount(Float.parseFloat(amount.getText()));
        PersistMembershipPlans.update(plan);
    }

    @FXML
    private void exitWindow(MouseEvent event) {
        Node source=(Node)event.getSource();
        Stage stage=(Stage)source.getScene().getWindow();
        stage.close();
    }
    
}
