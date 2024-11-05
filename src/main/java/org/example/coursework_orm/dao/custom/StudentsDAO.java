package org.example.coursework_orm.dao.custom;

import org.example.coursework_orm.dao.CrudDAO;
import org.example.coursework_orm.dto.StudentsDTO;
import org.example.coursework_orm.entity.Students;

import java.sql.SQLException;

public interface StudentsDAO extends CrudDAO<Students> {

    public Students searchByID(String studentID) throws SQLException;

    public  String getCurrentStudentID() throws SQLException;

}
