
package gui.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.model.Branch;
import com.persistence.PersistBranch;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class UpdateBranchController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextArea address;
    @FXML
    private JFXButton btnSubmit;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Branch branch = AdminWindowController.getBranch();
        name.setText(branch.getName());
        code.setText(branch.getCode());
        address.setText(branch.getAddress());
        name.setEditable(false);
    }    

    @FXML
    private void submit(MouseEvent event) throws SQLException {
        Branch branch = new Branch();
        branch.setName(name.getText());
        branch.setCode(code.getText());
        branch.setAddress(address.getText());
        PersistBranch.update(branch);
        
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
