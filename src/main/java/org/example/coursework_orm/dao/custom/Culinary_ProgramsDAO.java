package org.example.coursework_orm.dao.custom;

import org.example.coursework_orm.dao.CrudDAO;
import org.example.coursework_orm.entity.Culinary_Programs;

import java.sql.SQLException;

public interface Culinary_ProgramsDAO extends CrudDAO<Culinary_Programs> {

    public Culinary_Programs searchByID(String programID) throws SQLException;

}
