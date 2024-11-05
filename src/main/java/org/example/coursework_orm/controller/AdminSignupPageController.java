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
import org.example.coursework_orm.bo.custom.AdminBO;
import org.example.coursework_orm.bo.custom.AdmissionCoordinatorBO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

public class AdminSignupPageController {
    public AnchorPane root;
    public TextField txtUserID;
    public TextField txtUsername;
    public TextField txtPassword;

    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMIN);

    public void initialize(){
        getCurrentAdminID();
    }
    private void getCurrentAdminID() {
        try {

            String currentAdminID = adminBO.getCurrentAdminID();

            String nextAdminID = generateNextAdminID(currentAdminID);

            txtUserID.setText(nextAdminID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextAdminID(String currentAdminID) {

        if (currentAdminID != null) {
            String[] split = currentAdminID.split("A");
            int idNum = Integer.parseInt(split[1]);
            if (idNum < 99) {
                idNum++;
                return "A" + String.format("%03d", idNum);
            } else {
                return "Error: Maximum User ID reached (AC999)";
            }
        }
        return "A001";

    }

    public void btnSignUpOnAction(ActionEvent event) {
        String adminUserID = txtUserID.getText();
        String adminUsername = txtUsername.getText();
        String passwordWithoutEncrypt = txtPassword.getText();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String adminPassword =passwordEncoder.encode(passwordWithoutEncrypt);

        try{
            boolean isSaved = saveAdmin(adminUserID,adminUsername,adminPassword);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Admin Account Saved! Before exit from this page Make sure you have saved or write down these credentials! ").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private boolean saveAdmin(String adminUserID,String adminUsername,String adminPassword) throws SQLException {

        return adminBO.saveAdmin(adminUserID,adminUsername,adminPassword);

    }

    public void btnExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/admin_sign_selection.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("admin sign selection page");

        stage.centerOnScreen();
    }
}
