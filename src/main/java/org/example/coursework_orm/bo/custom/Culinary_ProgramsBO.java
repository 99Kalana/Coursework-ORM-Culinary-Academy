package org.example.coursework_orm.bo.custom;

import org.example.coursework_orm.bo.SuperBO;
import org.example.coursework_orm.dto.Culinary_ProgramsDTO;
import org.example.coursework_orm.entity.Culinary_Programs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Culinary_ProgramsBO extends SuperBO {

    public boolean add(Culinary_ProgramsDTO dto) throws SQLException;

    public boolean update(Culinary_ProgramsDTO dto) throws SQLException;

    public boolean delete(Culinary_ProgramsDTO dto) throws SQLException;

    public List<Culinary_ProgramsDTO> getAll() throws SQLException;

    public Culinary_ProgramsDTO searchByID(String programID) throws SQLException;

    public List<String> getIds() throws SQLException;

}
