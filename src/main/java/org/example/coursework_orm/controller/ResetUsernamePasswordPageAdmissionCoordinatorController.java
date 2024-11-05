package org.example.coursework_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.coursework_orm.bo.BOFactory;
import org.example.coursework_orm.bo.custom.ResetUsernamePasswordAdminBO;
import org.example.coursework_orm.bo.custom.ResetUsernamePasswordAdmissionCoordinatorBO;
import org.example.coursework_orm.entity.ResetUsernamePasswordAdmissionCoordinator;

import java.io.IOException;
import java.sql.SQLException;

public class ResetUsernamePasswordPageAdmissionCoordinatorController {
    public AnchorPane root;
    public TextField txtUserID;
    public TextField txtUsername;
    public TextField txtPassword;

    ResetUsernamePasswordAdmissionCoordinatorBO resetUsernamePasswordAdmissionCoordinatorBO= (ResetUsernamePasswordAdmissionCoordinatorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESET_USERNAME_PASSWORD_ADMISSION_COORDINATOR);

    public void btnResetUsernameOnAction(ActionEvent event) {
        String admissionCoUserID = txtUserID.getText();
        String admissionCoUsername = txtUsername.getText();
        String admissionCoPassword = txtPassword.getText();

        try {
            if (isValidUserUsername(admissionCoUserID,admissionCoPassword)){
                boolean isReset = resetUsername(admissionCoUserID,admissionCoUsername);
                if (isReset){
                    new Alert(Alert.AlertType.CONFIRMATION,"Username reset Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Username reset Unsuccessful!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid User ID or Password!").show();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Error occurred while resetting username"+e.getMessage()).show();
        }
    }

    private boolean isValidUserUsername(String admissionCoUserID, String admissionCoPassword) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorBO.isValidUserUsername(admissionCoUserID,admissionCoPassword);

    }

    private boolean resetUsername(String admissionCoUserID,String admissionCoUsername) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorBO.resetUsername(admissionCoUserID,admissionCoUsername);

    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/settings_page_admission_coordinator.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("admission coordinator settings page");

        stage.centerOnScreen();
    }

    public void btnResetPasswordOnAction(ActionEvent event) {
        String admissionCoUserID = txtUserID.getText();
        String admissionCoUsername = txtUsername.getText();
        String admissionCoPassword = txtPassword.getText();

        try {
            if (isValidUserPassword(admissionCoUserID,admissionCoUsername)){
                boolean isReset = resetPassword(admissionCoUserID,admissionCoPassword);
                if (isReset){
                    new Alert(Alert.AlertType.CONFIRMATION,"Password reset Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Password reset Unsuccessful!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid User ID or Username!").show();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Error occurred while resetting password"+e.getMessage()).show();
        }
    }

    private boolean isValidUserPassword(String admissionCoUserID, String admissionCoUsername) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorBO.isValidUserPassword(admissionCoUserID,admissionCoUsername);

    }

    private boolean resetPassword(String admissionCoUserID,String admissionCoPassword) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorBO.resetPassword(admissionCoUserID,admissionCoPassword);

    }

}
