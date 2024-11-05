package org.example.coursework_orm.bo.custom;

import org.example.coursework_orm.bo.SuperBO;
import org.example.coursework_orm.dto.StudentsDTO;
import org.example.coursework_orm.entity.Students;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentsBO extends SuperBO {

    public boolean add(StudentsDTO dto) throws SQLException;

    public boolean update(StudentsDTO dto) throws SQLException;

    public boolean delete(StudentsDTO dto) throws SQLException;

    public List<StudentsDTO> getAll() throws SQLException;

    public StudentsDTO searchByID(String studentID) throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentStudentID() throws SQLException;

}
