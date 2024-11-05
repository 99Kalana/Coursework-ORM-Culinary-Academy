package org.example.coursework_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsPageAdminController {
    public AnchorPane root;

    public void btnResetUsernamePasswordOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/reset_username_password_page_admin.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("reset username password admin");

        stage.centerOnScreen();
    }

    public void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/admin_sign_in_page.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("sign in admin");

        stage.centerOnScreen();
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/homepage_admin.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("homepage admin");

        stage.centerOnScreen();
    }
}
