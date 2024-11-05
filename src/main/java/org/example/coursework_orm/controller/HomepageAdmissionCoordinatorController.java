package org.example.coursework_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageAdmissionCoordinatorController {
    public AnchorPane root;
    public Label lblAdmissionCoordinatorUsername;


    public void setAdmissionCoordinatorUsername(String admissionCoUsername) {
        lblAdmissionCoordinatorUsername.setText(admissionCoUsername);
    }

    public void btnStudentsOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/student.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("students details handle admission coordinator");

        stage.centerOnScreen();
    }

    public void btnCulinaryProgramsOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/culinary_programs.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("program details handle admission coordinator");

        stage.centerOnScreen();
    }

    public void btnSettingsOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/settings_page_admission_coordinator.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("settings admission coordinator");

        stage.centerOnScreen();
    }
}
