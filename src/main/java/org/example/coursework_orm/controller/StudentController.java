package org.example.coursework_orm.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
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
import org.example.coursework_orm.entity.Admin;
import org.example.coursework_orm.entity.Admission_Coordinator;
import org.example.coursework_orm.entity.Culinary_Programs;
import com.jfoenix.controls.JFXButton;
import org.example.coursework_orm.model.tm.StudentsTM;
import org.example.coursework_orm.util.StudentRegex;
import org.example.coursework_orm.util.StudentTextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class StudentController {
    public AnchorPane root;
    public JFXComboBox cmbSelectedProgram;
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
    public TableColumn <?,?> colProgramID;
    public Label lblProgramName;


    Culinary_ProgramsBO culinaryProgramsBO = (Culinary_ProgramsBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.CULINARY_PROGRAM);

    StudentsBO studentsBO = (StudentsBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENTS);

    public void initialize(){
        setCellValueFactory();
        setDate();
        getCurrentStudentID();
        getProgramID();
        loadAllStudents();
    }

    private void setDate(){
        LocalDate now =LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void getCurrentStudentID() {
        try {

            String currentStudentID = studentsBO.getCurrentStudentID();

            String nextStudentID = generateNextStudentID(currentStudentID);

            txtStudentID.setText(nextStudentID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextStudentID(String currentStudentID) {

        if (currentStudentID != null) {
            String[] split = currentStudentID.split("ST");
            int idNum = Integer.parseInt(split[1]);
            if (idNum < 999) {
                idNum++;
                return "ST" + String.format("%04d", idNum);
            } else {
                return "Error: Maximum bill ID reached (ST9999)";
            }
        }
        return "ST0001";

    }


    public void btnAddOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();
        Date date = java.sql.Date.valueOf(lblDate.getText());
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText();
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String address = txtAddress.getText();
        String selectedCourse = lblProgramName.getText();

        Culinary_Programs Program_ID =  new Culinary_Programs();
        Program_ID.setProgramID((String) cmbSelectedProgram.getValue());
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin> admins = new ArrayList<>();

        try{
            StudentsDTO studentsDTO = new StudentsDTO(studentID,date,firstName,lastName,email,mobileNumber,address,selectedCourse,Program_ID,admissionCoordinators,admins);

            boolean isSaved = studentsBO.add(studentsDTO);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Student details added!").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error in add student details!").show();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        loadAllStudents();



    }

    //===============================================================================================================

    /*public void btnUpdateOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();
        Date date = java.sql.Date.valueOf(lblDate.getText());
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText();
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String address = txtAddress.getText();
        String selectedCourse = lblProgramName.getText();

        Culinary_Programs Program_ID =  new Culinary_Programs();
        Program_ID.setProgramID((String) cmbSelectedProgram.getValue());
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin> admins = new ArrayList<>();

        try{
            StudentsDTO studentsDTO = new StudentsDTO(studentID,date,firstName,lastName,email,mobileNumber,address,selectedCourse,Program_ID,admissionCoordinators,admins);

            boolean isUpdated = studentsBO.update(studentsDTO);

            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Student details updated!").show();
                clearFields();
                getCurrentStudentID();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error in update student details!").show();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        loadAllStudents();

    }*/

    //==============================================================================================================================
    public void btnUpdateOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();
        Date date = java.sql.Date.valueOf(lblDate.getText());
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText();
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String address = txtAddress.getText();

        // Create Culinary_Programs object for the selected program
        Culinary_Programs newProgram = new Culinary_Programs();
        newProgram.setProgramID((String) cmbSelectedProgram.getValue());

        // Retrieve the existing student data
        try {
            StudentsDTO existingStudent = studentsBO.searchByID(studentID);
            if (existingStudent != null) {
                // Combine existing selected course with the new one
                String existingCourses = existingStudent.getSelectedCourse();
                String newSelectedCourse = lblProgramName.getText();

                // Assuming courses are separated by commas, adjust as needed
                String combinedCourses = existingCourses + ", " + newSelectedCourse;


                StudentsDTO studentsDTO = new StudentsDTO(studentID, date, firstName, lastName, email,
                        mobileNumber, address, combinedCourses,
                        newProgram, null, null);

                boolean isUpdated = studentsBO.update(studentsDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student details updated!").show();
                    clearFields();
                    getCurrentStudentID();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error in updating student details!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Student not found for update!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error during update: " + e.getMessage()).show();
        }
        loadAllStudents();
    }

    //==============================================================================================================================

    public void btnDeleteOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();
        Date date = java.sql.Date.valueOf(lblDate.getText());
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText();
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String address = txtAddress.getText();
        String selectedCourse = lblProgramName.getText();

        Culinary_Programs Program_ID =  new Culinary_Programs();
        Program_ID.setProgramID((String) cmbSelectedProgram.getValue());
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin> admins = new ArrayList<>();

        try{
            StudentsDTO studentsDTO = new StudentsDTO(studentID,date,firstName,lastName,email,mobileNumber,address,selectedCourse,Program_ID,admissionCoordinators,admins);

            boolean isDeleted = studentsBO.delete(studentsDTO);

            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Student details deleted!").show();
                clearFields();
                getCurrentStudentID();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error in delete student details!").show();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        loadAllStudents();
    }

    public void btnClearOnAction(ActionEvent event) {
        clearFields();
        getCurrentStudentID();
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

    public void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/homepage_admission_coordinator.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("homepage admission coordinator");

        stage.centerOnScreen();
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

    //================================================================================================================================

   /* public void txtSearchOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();

        try {
            StudentsDTO studentsDTO = studentsBO.searchByID(studentID);
            if (studentsDTO != null){
                txtFirstname.setText(studentsDTO.getFirstName());
                txtLastname.setText(studentsDTO.getLastName());
                txtEmail.setText(studentsDTO.getEmail());
                txtMobileNumber.setText(String.valueOf(studentsDTO.getMobileNumber()));
                txtAddress.setText(studentsDTO.getAddress());
                lblProgramName.setText(studentsDTO.getSelectedCourse());
                //cmbSelectedProgram.setValue(studentsDTO.getCulinaryPrograms().getProgramID());

                // Ensure ComboBox is populated before setting the value
                getProgramID();
                String programID = studentsDTO.getCulinaryPrograms().getProgramID();

                if (programID != null && cmbSelectedProgram.getItems().contains(programID)) {
                    cmbSelectedProgram.setValue(programID);
                } else {
                    System.out.println("Program ID not found in ComboBox items: " + programID);
                    new Alert(Alert.AlertType.WARNING, "Selected program not found in available options!").show();
                }

            } else {
                new Alert(Alert.AlertType.WARNING, "Student data not found!").show();
                clearFields();
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/
    //======================================================================================================================================

    public void txtSearchOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();

        try {
            StudentsDTO studentsDTO = studentsBO.searchByID(studentID);
            if (studentsDTO != null) {

                txtFirstname.setText(studentsDTO.getFirstName());
                txtLastname.setText(studentsDTO.getLastName());
                txtEmail.setText(studentsDTO.getEmail());
                txtMobileNumber.setText(String.valueOf(studentsDTO.getMobileNumber()));
                txtAddress.setText(studentsDTO.getAddress());


                String selectedCourses = studentsDTO.getSelectedCourse();


                if (selectedCourses != null && !selectedCourses.isEmpty()) {

                    String[] programs = selectedCourses.split(",");


                    StringBuilder programNames = new StringBuilder();
                    for (String program : programs) {
                        if (programNames.length() > 0) {
                            programNames.append(", ");
                        }
                        programNames.append(program.trim());
                    }


                    lblProgramName.setText(programNames.toString());
                } else {
                    lblProgramName.setText("No programs selected");
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Student data not found!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    //======================================================================================================================================

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        StudentRegex.setTextColour(StudentTextField.EMAIL,txtEmail);
    }

    public void txtMobileNumberOnKeyReleased(KeyEvent keyEvent) {
        StudentRegex.setTextColour(StudentTextField.MOBILE_NUMBER,txtMobileNumber);
    }

    public void txtStudentIDOnKeyReleased(KeyEvent keyEvent) {
        StudentRegex.setTextColour(StudentTextField.STUDENT_ID,txtStudentID);
    }
}
