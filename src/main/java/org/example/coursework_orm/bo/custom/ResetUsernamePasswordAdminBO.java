package org.example.coursework_orm.bo.custom;

import org.example.coursework_orm.bo.SuperBO;

import java.sql.SQLException;

public interface ResetUsernamePasswordAdminBO extends SuperBO {
    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException;

    public boolean resetPassword(String adminUserID,String adminPassword) throws SQLException;

    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException;

    public boolean resetUsername(String adminUserID,String adminUsername) throws SQLException;
}
