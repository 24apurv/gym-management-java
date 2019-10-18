
package gui.admin;

import com.db.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.model.Branch;
import com.model.MembershipPlans;
import com.model.Users;
import com.notify.Notification;
import com.persistence.PersistBranch;
import com.persistence.PersistMembershipPlans;
import com.persistence.PersistUsers;
import com.report.ReportGenerator;
import gui.user.UserWindowController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminWindowController implements Initializable {

    private StackPane stackPane;
    @FXML
    private BorderPane parent;
    @FXML
    private Label lblHello;
    @FXML
    private TableColumn<Branch, String> col_BranchName;
    @FXML
    private TableColumn<Branch, String> col_BranchCode;
    @FXML
    private TableColumn<Branch, String> col_Address;
    @FXML
    private JFXButton btnAddBranch;
    @FXML
    private JFXButton btnRemoveBranch;
    @FXML
    private JFXButton btnEditBranch;
    @FXML
    private TableColumn<Users, String> col_UsersName;
    @FXML
    private TableColumn<Users, String> col_Username;
    @FXML
    private TableColumn<Users, String> col_Branch;
    @FXML
    private TableColumn<Users, String> col_Privilege;
    @FXML
    private JFXButton btnAddUser;
    @FXML
    private JFXButton btnRemoveUser;
    @FXML
    private JFXButton btnUpdateUser;
    
    ObservableList<Branch> branchList;
    
    ObservableList<Users> usersList;
    
    ObservableList<MembershipPlans> plansList;
    
    @FXML
    private TableView<Branch> branchTable;
    @FXML
    private TableView<Users> usersTable;
    
    private static Branch branch;
    
    private static Users user;
    
    private static MembershipPlans plan;
    
    public static MembershipPlans getPlan()
    {
        return plan;
    }
    
    public static Branch getBranch()
    {
        return branch;
    }
    
    public static Users getUser()
    {
        return user;
    }
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private JFXTextField usersname;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private TableView<MembershipPlans> plansTable;
    @FXML
    private TableColumn<MembershipPlans, String> col_planName;
    @FXML
    private TableColumn<MembershipPlans, Integer> col_planDuration;
    @FXML
    private TableColumn<MembershipPlans, Float> col_planAmount;
    @FXML
    private JFXButton btnAddPlan;
    @FXML
    private JFXButton btnRemovePlan;
    @FXML
    private JFXButton btnUpdatePlan;
    @FXML
    private JFXTextArea emailText;
    @FXML
    private JFXButton btnSend;
    @FXML
    private JFXTextField emailSubject;
    @FXML
    private JFXButton btnGenderDistribution;
    @FXML
    private JFXButton btnAgeDistribution;
    @FXML
    private JFXButton btnMonthlySales;
    @FXML
    private JFXButton btnBranchSales;
    @FXML
    private JFXButton btnPlanSales;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lblHello.setText("Hello, "+PersistUsers.getUsers().getUsername());
            branchList = PersistBranch.retrieveAll();
            usersList = PersistUsers.retrieveAll();
            plansList = PersistMembershipPlans.retrieveAll();
            
            col_BranchName.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_BranchCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            col_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
            branchTable.setItems(branchList);
            
            col_UsersName.setCellValueFactory(new PropertyValueFactory<>("usersname"));
            col_Username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_Branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
            col_Privilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));;
            usersTable.setItems(usersList);
            
            col_planName.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_planDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            col_planAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            plansTable.setItems(plansList);
            
            
            username.setText(PersistUsers.getUsers().getUsername());
            usersname.setText(PersistUsers.getUsers().getUsersname());
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void exitApplication(MouseEvent event) throws SQLException {
        DatabaseConnection.getConnection().close();
        System.exit(0);
    }

    @FXML
    private void addBranch(MouseEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddBranch.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void removeBranch(MouseEvent event) {
        branch = branchTable.getSelectionModel().getSelectedItem();
        if(branch==null)
        {
            String title="Warning";
            String content="No record selected";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            return;
        }
        String title="Delete";
        String content="Are you sure you want to delete customer record?";
        JFXDialogLayout dialogContent=new JFXDialogLayout();
        dialogContent.setHeading(new Text(title));
        dialogContent.setBody(new Text(content));
        JFXButton yes=new JFXButton("Yes");
        yes.setButtonType(JFXButton.ButtonType.RAISED);
        JFXButton no=new JFXButton("No");
        no.setButtonType(JFXButton.ButtonType.RAISED);
        dialogContent.setActions(yes,no);
        JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
        yes.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            try {
                PersistBranch.remove(branch.getCode());
            } catch (SQLException ex) {
                Logger.getLogger(UserWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
                dialog.close();
        }
        });
        no.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            dialog.close();
        }
        });
        dialog.show();
    }

    @FXML
    private void editBranch(MouseEvent event) throws IOException {
        branch = branchTable.getSelectionModel().getSelectedItem();
        if(branch==null)
        {
            String title="Warning";
            String content="No record selected";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            return;
        }
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateBranch.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addUser(MouseEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void removeUser(MouseEvent event) {
        user = usersTable.getSelectionModel().getSelectedItem();
        if(user==null)
        {
            String title="Warning";
            String content="No record selected";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            return;
        }
        String title="Delete";
        String content="Are you sure you want to delete customer record?";
        JFXDialogLayout dialogContent=new JFXDialogLayout();
        dialogContent.setHeading(new Text(title));
        dialogContent.setBody(new Text(content));
        JFXButton yes=new JFXButton("Yes");
        yes.setButtonType(JFXButton.ButtonType.RAISED);
        JFXButton no=new JFXButton("No");
        no.setButtonType(JFXButton.ButtonType.RAISED);
        dialogContent.setActions(yes,no);
        JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
        yes.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            try {
                PersistUsers.remove(user.getUsername());
            } catch (SQLException ex) {
                Logger.getLogger(UserWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
                dialog.close();
        }
        });
        no.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            dialog.close();
        }
        });
        dialog.show();
    }

    @FXML
    private void updateUser(MouseEvent event) throws IOException {
        user = usersTable.getSelectionModel().getSelectedItem();
        if(user==null)
        {
            String title="Warning";
            String content="No record selected";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            return;
        }
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateUser.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitUser(MouseEvent event) throws SQLException {
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
        user.setUsersname(usersname.getText());
        user.setPassword(password.getText());
        user.setBranch(PersistUsers.getUsers().getBranch());
        user.setPrivilege(PersistUsers.getUsers().getPrivilege());
        PersistUsers.update(user);
        
    }

    @FXML
    private void addPlan(MouseEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPlan.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void removePlan(MouseEvent event) {
        plan = plansTable.getSelectionModel().getSelectedItem();
        if(plan==null)
        {
            String title="Warning";
            String content="No record selected";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            return;
        }
        String title="Delete";
        String content="Are you sure you want to delete plan record?";
        JFXDialogLayout dialogContent=new JFXDialogLayout();
        dialogContent.setHeading(new Text(title));
        dialogContent.setBody(new Text(content));
        JFXButton yes=new JFXButton("Yes");
        yes.setButtonType(JFXButton.ButtonType.RAISED);
        JFXButton no=new JFXButton("No");
        no.setButtonType(JFXButton.ButtonType.RAISED);
        dialogContent.setActions(yes,no);
        JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
        yes.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            try {
                PersistMembershipPlans.remove(plan.getName());
            } catch (SQLException ex) {
                Logger.getLogger(UserWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
                dialog.close();
        }
        });
        no.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            dialog.close();
        }
        });
        dialog.show();
    }

    @FXML
    private void updatePlan(MouseEvent event) throws IOException {
        plan = plansTable.getSelectionModel().getSelectedItem();
        if(plan==null)
        {
            String title="Warning";
            String content="No record selected";
            JFXDialogLayout dialogContent=new JFXDialogLayout();
            dialogContent.setHeading(new Text(title));
            dialogContent.setBody(new Text(content));
            JFXDialog dialog=new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
            dialog.applyCss();
            dialog.show();
            return;
        }
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UpdatePlan.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sendNotifications(MouseEvent event) throws SQLException {
        Notification.notify(emailSubject.getText(), emailText.getText());
    }

    @FXML
    private void genderDistribution(MouseEvent event) throws SQLException, IOException {
        ReportGenerator.genderDistribution();
    }

    @FXML
    private void ageDistribution(MouseEvent event) throws SQLException, IOException {
        ReportGenerator.ageDistribution();
    }

    @FXML
    private void monthlySales(MouseEvent event) throws SQLException, IOException {
        ReportGenerator.monthlySales();
    }

    @FXML
    private void branchSales(MouseEvent event) throws SQLException, IOException {
        ReportGenerator.branchSales();
    }

    @FXML
    private void planSales(MouseEvent event) throws SQLException, IOException {
        ReportGenerator.planSales();
    }
    
}
