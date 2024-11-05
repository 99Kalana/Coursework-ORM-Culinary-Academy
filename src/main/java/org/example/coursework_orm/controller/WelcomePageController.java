package org.example.coursework_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePageController {
    public AnchorPane root;

    public void btnEnterOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/role_selection_page.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("Select who you are?");

        stage.centerOnScreen();
    }
}
