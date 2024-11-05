package org.example.coursework_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageAdminController {
    public AnchorPane root;
    public Label lblAdminUsername;

    // Method to set the admin username on the label
    public void setAdminUsername(String adminUsername) {
        lblAdminUsername.setText(adminUsername);
    }

    public void btnViewStudentsOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/student_view.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("students view admin");

        stage.centerOnScreen();
    }

    public void btnViewCulinaryProgramsOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/culinary_programs_view.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("culinary programs view admin");

        stage.centerOnScreen();
    }

    public void btnSettingsOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/settings_page_admin.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("settings admin");

        stage.centerOnScreen();
    }
}
