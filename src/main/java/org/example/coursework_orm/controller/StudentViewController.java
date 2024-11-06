package org.example.coursework_orm.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.coursework_orm.bo.BOFactory;
import org.example.coursework_orm.bo.custom.Culinary_ProgramsBO;
import org.example.coursework_orm.bo.custom.StudentsBO;
import org.example.coursework_orm.dto.Culinary_ProgramsDTO;
import org.example.coursework_orm.dto.StudentsDTO;
import org.example.coursework_orm.model.tm.StudentsTM;
import org.example.coursework_orm.util.StudentRegex;
import org.example.coursework_orm.util.StudentTextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentViewController {
    public AnchorPane root;
    public TextField txtStudentID;
    public Label lblDate;
    public TextField txtFirstname;
    public TextField txtLastname;
    public TextField txtEmail;
    public TextField txtAddress;
    public TextField txtMobileNumber;
    public TableView <StudentsTM> tblStudents;
    public TableColumn <?,?> colStudentID;
    public TableColumn <?,?> colDate;
    public TableColumn <?,?> colFirstname;
    public TableColumn <?,?> colLastname;
    public TableColumn <?,?> colEmail;
    public TableColumn <?,?> colMobileNumber;
    public TableColumn <?,?> colAddress;
    public TableColumn <?,?> colSelectedProgram;
    public Label lblProgramName;
    public JFXComboBox cmbSelectedProgram;

    Culinary_ProgramsBO culinaryProgramsBO = (Culinary_ProgramsBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.CULINARY_PROGRAM);

    StudentsBO studentsBO = (StudentsBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENTS);

    public void initialize(){
        setCellValueFactory();
        getProgramID();
        loadAllStudents();
    }



    public void txtSearchOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();

        try {
            StudentsDTO studentsDTO = studentsBO.searchByID(studentID);
            if (studentsDTO != null){
                txtFirstname.setText(studentsDTO.getFirstName());
                txtLastname.setText(studentsDTO.getLastName());
                lblDate.setText(String.valueOf(studentsDTO.getDate()));
                txtEmail.setText(studentsDTO.getEmail());
                txtMobileNumber.setText(String.valueOf(studentsDTO.getMobileNumber()));
                txtAddress.setText(studentsDTO.getAddress());
                lblProgramName.setText(studentsDTO.getSelectedCourse());
                cmbSelectedProgram.setValue(studentsDTO.getCulinaryPrograms().getProgramID());
            } else {
                new Alert(Alert.AlertType.WARNING, "Student data not found!").show();
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
        txtStudentID.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        txtEmail.setText("");
        txtMobileNumber.setText("");
        txtAddress.setText("");
        lblProgramName.setText("");
        cmbSelectedProgram.setValue("");
    }

    private void setCellValueFactory(){
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSelectedProgram.setCellValueFactory(new PropertyValueFactory<>("selectedCourse"));
    }

    private void getProgramID() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {

            List<String> idList = culinaryProgramsBO.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbSelectedProgram.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cmbSelectedProgramOnAction(ActionEvent event) {
        String programID = (String) cmbSelectedProgram.getValue();

        try {

            Culinary_ProgramsDTO culinaryProgramsDTO = culinaryProgramsBO.searchByID(programID);

            lblProgramName.setText(culinaryProgramsDTO.getProgramName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllStudents(){
        ObservableList<StudentsTM> obList = FXCollections.observableArrayList();

        try{
            List<StudentsDTO> studentsDTOList = studentsBO.getAll();

            for (StudentsDTO studentsDTO: studentsDTOList){
                StudentsTM tm = new StudentsTM(studentsDTO.getStudentID(),studentsDTO.getDate(),studentsDTO.getFirstName(),studentsDTO.getLastName(),studentsDTO.getEmail(),studentsDTO.getMobileNumber(),studentsDTO.getAddress(),studentsDTO.getSelectedCourse());
                obList.add(tm);
            }

            tblStudents.setItems(obList);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtStudentIDOnKeyReleased(KeyEvent keyEvent) {
        StudentRegex.setTextColour(StudentTextField.STUDENT_ID,txtStudentID);
    }
}
