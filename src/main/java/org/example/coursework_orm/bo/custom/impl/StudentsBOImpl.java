package org.example.coursework_orm.bo.custom.impl;

import org.example.coursework_orm.bo.custom.StudentsBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.example.coursework_orm.dao.custom.Culinary_ProgramsDAO;
import org.example.coursework_orm.dao.custom.StudentsDAO;
import org.example.coursework_orm.dto.Culinary_ProgramsDTO;
import org.example.coursework_orm.dto.StudentsDTO;
import org.example.coursework_orm.entity.Culinary_Programs;
import org.example.coursework_orm.entity.Students;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsBOImpl implements StudentsBO {

    StudentsDAO studentsDAO = (StudentsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.STUDENTS);

    public boolean add(StudentsDTO dto) throws SQLException {

        Students students= new Students(dto.getStudentID(), dto.getDate(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getMobileNumber(), dto.getAddress(), dto.getSelectedCourse(),dto.getCulinaryPrograms(),dto.getAdmissionCoordinators(), dto.getAdmins());
        return studentsDAO.add(students);

    }

    public boolean update(StudentsDTO dto) throws SQLException {

        Students students= new Students(dto.getStudentID(), dto.getDate(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getMobileNumber(), dto.getAddress(), dto.getSelectedCourse(),dto.getCulinaryPrograms(),dto.getAdmissionCoordinators(), dto.getAdmins());
        return studentsDAO.update(students);

    }

    public boolean delete(StudentsDTO dto) throws SQLException {

        Students students= new Students(dto.getStudentID(), dto.getDate(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getMobileNumber(), dto.getAddress(), dto.getSelectedCourse(),dto.getCulinaryPrograms(),dto.getAdmissionCoordinators(), dto.getAdmins());
        return studentsDAO.delete(students);

    }

    public List<StudentsDTO> getAll() throws SQLException {


        List<StudentsDTO>studentsDTOList=new ArrayList<>();

        List<Students>studentsList=studentsDAO.getAll();

        for(Students students:studentsList){
            studentsDTOList.add(new StudentsDTO(students.getStudentID(),students.getDate(),students.getFirstName(),students.getLastName(),students.getEmail(),students.getMobileNumber(),students.getAddress(),students.getSelectedCourse(),students.getCulinaryPrograms(),students.getAdmissionCoordinators(),students.getAdmins()));
        }

        return studentsDTOList;

    }

    public StudentsDTO searchByID(String studentID) throws SQLException {
        Students students= studentsDAO.searchByID(studentID);
        if (students != null) {

            return new StudentsDTO(students.getStudentID(),students.getDate(), students.getFirstName(), students.getLastName(),students.getEmail(),students.getMobileNumber(),students.getAddress(),students.getSelectedCourse(),students.getCulinaryPrograms(),students.getAdmissionCoordinators(),students.getAdmins());
        }
        return null;
    }

    public List<String> getIds() throws SQLException {
        List<String> idList = new ArrayList<>();
        List<String> studentIDs = studentsDAO.getIds();

        for (String id : studentIDs) {
            idList.add(id);
        }

        return idList;
    }

    public  String getCurrentStudentID() throws SQLException {

        return  studentsDAO.getCurrentStudentID();

    }

}
