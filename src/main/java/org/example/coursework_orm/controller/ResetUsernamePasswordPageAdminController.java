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

import java.io.IOException;
import java.sql.SQLException;

public class ResetUsernamePasswordPageAdminController {
    public AnchorPane root;
    public TextField txtUserID;
    public TextField txtUsername;
    public TextField txtPassword;

    ResetUsernamePasswordAdminBO resetUsernamePasswordAdminBO= (ResetUsernamePasswordAdminBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESET_USERNAME_PASSWORD_ADMIN);

    public void btnResetUsernameOnAction(ActionEvent event) {
        String adminUserID = txtUserID.getText();
        String adminUsername = txtUsername.getText();
        String adminPassword = txtPassword.getText();

        try {
            if (isValidUserUsername(adminUserID,adminPassword)){
                boolean isReset = resetUsername(adminUserID,adminUsername);
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

    private boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException {

        return resetUsernamePasswordAdminBO.isValidUserUsername(adminUserID,adminPassword);

    }

    private boolean resetUsername(String adminUserID,String adminUsername) throws SQLException {

        return resetUsernamePasswordAdminBO.resetUsername(adminUserID, adminUsername);

    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/settings_page_admin.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("admin settings page");

        stage.centerOnScreen();
    }

    public void btnResetPasswordOnAction(ActionEvent event){
        String adminUserIDs = txtUserID.getText();
        String adminUsernames = txtUsername.getText();
        String adminPasswords = txtPassword.getText();

        try {
            if (isValidUserPassword(adminUserIDs,adminUsernames)){
                boolean isReset = resetPassword(adminUserIDs,adminPasswords);
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

    private boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException {

        return resetUsernamePasswordAdminBO.isValidUserPassword(adminUserID,adminUsername);

    }

    private boolean resetPassword(String adminUserID,String adminPassword) throws SQLException {

        return resetUsernamePasswordAdminBO.resetPassword(adminUserID, adminPassword);

    }
}
