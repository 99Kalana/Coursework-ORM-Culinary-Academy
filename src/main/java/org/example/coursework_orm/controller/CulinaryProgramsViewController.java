package org.example.coursework_orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.coursework_orm.bo.BOFactory;
import org.example.coursework_orm.bo.custom.Culinary_ProgramsBO;
import org.example.coursework_orm.dto.Culinary_ProgramsDTO;
import org.example.coursework_orm.model.tm.CulinaryProgramsTM;
import org.example.coursework_orm.util.CulinaryProgramRegex;
import org.example.coursework_orm.util.CulinaryProgramTextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CulinaryProgramsViewController {
    public AnchorPane root;
    public TextField txtProgramID;
    public TextField txtProgramName;
    public TextField txtDuration;
    public TextField txtFee;
    public TableView <CulinaryProgramsTM> tblCulinaryPrograms;
    public TableColumn <?,?> colProgramID;
    public TableColumn <?,?> colProgramName;
    public TableColumn <?,?> colDuration;
    public TableColumn <?,?> colFee;

    Culinary_ProgramsBO culinaryProgramsBO = (Culinary_ProgramsBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.CULINARY_PROGRAM);

    public void initialize(){
        setCellValueFactory();
        loadAllCulinaryPrograms();
    }
    public void txtSearchOnAction(ActionEvent event) {
        String programID = txtProgramID.getText();

        try {
            Culinary_ProgramsDTO culinaryProgramsDTO = culinaryProgramsBO.searchByID(programID);
            if (culinaryProgramsDTO != null){
                txtProgramName.setText(culinaryProgramsDTO.getProgramName());
                txtDuration.setText(culinaryProgramsDTO.getDuration());
                txtFee.setText(culinaryProgramsDTO.getFee());
            } else {
                new Alert(Alert.AlertType.WARNING, "Culinary Program data not found!").show();
                clearFields();
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/homepage_admin.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("homepage admin");

        stage.centerOnScreen();
    }

    public void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    public void clearFields(){
        txtProgramID.setText("");
        txtProgramName.setText("");
        txtDuration.setText("");
        txtFee.setText("");
    }

    private void setCellValueFactory(){
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    private void loadAllCulinaryPrograms(){
        ObservableList<CulinaryProgramsTM> obList = FXCollections.observableArrayList();

        try{
            List<Culinary_ProgramsDTO> culinaryProgramsDTOList = culinaryProgramsBO.getAll();

            for (Culinary_ProgramsDTO culinaryProgramsDTO: culinaryProgramsDTOList){
                CulinaryProgramsTM tm = new CulinaryProgramsTM(culinaryProgramsDTO.getProgramID(), culinaryProgramsDTO.getProgramName(), culinaryProgramsDTO.getDuration(), culinaryProgramsDTO.getFee());
                obList.add(tm);
            }

            tblCulinaryPrograms.setItems(obList);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtProgramIDOnKeyReleased(KeyEvent keyEvent) {
        CulinaryProgramRegex.setTextColour(CulinaryProgramTextField.PROGRAM_ID,txtProgramID);
    }
}
