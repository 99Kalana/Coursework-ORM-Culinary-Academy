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
import org.example.coursework_orm.bo.custom.AdmissionCoordinatorBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.IOException;
import java.sql.SQLException;

public class AdmissionCoordinatorSignupPageController {
    public AnchorPane root;
    public TextField txtUserID;
    public TextField txtUsername;
    public TextField txtPassword;

    AdmissionCoordinatorBO admissionCoordinatorBO = (AdmissionCoordinatorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMISSION_COORDINATOR);

    public void initialize(){
        getCurrentAdmissionCoordinatorID();
    }
    private void getCurrentAdmissionCoordinatorID() {
        try {

            String currentAdmissionCoordinatorID = admissionCoordinatorBO.getCurrentAdmissionCoordinatorID();

            String nextAdmissionCoordinatorID = generateNextAdmissionCoordinatorID(currentAdmissionCoordinatorID);

            txtUserID.setText(nextAdmissionCoordinatorID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextAdmissionCoordinatorID(String currentAdmissionCoordinatorID) {

        if (currentAdmissionCoordinatorID != null) {
            String[] split = currentAdmissionCoordinatorID.split("AC");
            int idNum = Integer.parseInt(split[1]);
            if (idNum < 99) {
                idNum++;
                return "AC" + String.format("%03d", idNum);
            } else {
                return "Error: Maximum User ID reached (AC999)";
            }
        }
        return "AC001";

    }

    public void btnSignUpOnAction(ActionEvent event) {
        String admissionCoUserID = txtUserID.getText();
        String admissionCoUsername = txtUsername.getText();
        String passwordWithoutEncrypt = txtPassword.getText();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admissionCoPassword =passwordEncoder.encode(passwordWithoutEncrypt);

        try{
            boolean isSaved = saveAdmissionCoordinator(admissionCoUserID,admissionCoUsername,admissionCoPassword);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "AdmissionCoordinator Account Saved! Before exit from this page Make sure you have saved or write down these credentials! ").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private boolean saveAdmissionCoordinator(String admissionCoUserID,String admissionCoUsername,String admissionCoPassword) throws SQLException {

        return admissionCoordinatorBO.saveAdmissionCoordinator(admissionCoUserID,admissionCoUsername,admissionCoPassword);

    }

    public void btnExitOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/admission_coordinator_sign_selection.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("admission coordinator sign selection page");

        stage.centerOnScreen();
    }
}
