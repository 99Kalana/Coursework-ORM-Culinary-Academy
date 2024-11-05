package org.example.coursework_orm.bo.custom;

import org.example.coursework_orm.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AdminBO extends SuperBO {
    public boolean saveAdmin(String adminUserID,String adminUsername,String adminPassword)throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentAdminID() throws SQLException;
}
