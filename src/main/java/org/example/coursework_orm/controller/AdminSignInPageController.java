package org.example.coursework_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.coursework_orm.bo.BOFactory;
import org.example.coursework_orm.bo.custom.AdminSignInBO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

public class AdminSignInPageController {
    public AnchorPane root;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public TextField txtPasswordVisible;
    public CheckBox chkShowPassword;

    AdminSignInBO adminSignInBO = (AdminSignInBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMIN_SIGN_IN);

    @FXML
    public void initialize() {
        if (txtPassword != null && txtPasswordVisible != null) {
            txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());
        }
    }

    @FXML
    public void togglePasswordVisibility(ActionEvent event) {
        if (chkShowPassword.isSelected()) {
            txtPasswordVisible.setVisible(true);
            txtPassword.setVisible(false);
        } else {
            txtPasswordVisible.setVisible(false);
            txtPassword.setVisible(true);
        }
    }


    public void hyperlinkForgotPasswordOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/reset_username_password_page_admin_sign.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("admin reset username password page");

        stage.centerOnScreen();
    }

    public void btnSignInOnAction(ActionEvent event) throws IOException {
        String adminUsername = txtUsername.getText();
        //String passwordWithoutEncrypt = txtPassword.getText();
        String adminPassword = txtPassword.getText();

        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String adminPassword =passwordEncoder.encode(passwordWithoutEncrypt);*/

        try{
            checkCredential(adminUsername,adminPassword,root);
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void checkCredential(String adminUsername,String adminPassword,AnchorPane root) throws SQLException, IOException {
        adminSignInBO.checkCredential(adminUsername,adminPassword,root);
    }

    public void btnExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/admin_sign_selection.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("admin sign selection page");

        stage.centerOnScreen();
    }


}
