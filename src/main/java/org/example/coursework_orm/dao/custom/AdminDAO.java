package org.example.coursework_orm.dao.custom;

import org.example.coursework_orm.dao.CrudDAO;
import org.example.coursework_orm.entity.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAO extends CrudDAO<Admin> {
    public boolean saveAdmin(String adminUserID,String adminUsername,String adminPassword)throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentAdminID() throws SQLException;
}
