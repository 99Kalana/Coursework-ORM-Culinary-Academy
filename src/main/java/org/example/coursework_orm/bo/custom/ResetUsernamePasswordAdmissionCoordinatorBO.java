package org.example.coursework_orm.bo.custom;

import org.example.coursework_orm.bo.SuperBO;

import java.sql.SQLException;

public interface ResetUsernamePasswordAdmissionCoordinatorBO extends SuperBO {
    public boolean isValidUserPassword(String admissionCoUserID, String admissionCoUsername) throws SQLException;

    public boolean resetPassword(String admissionCoUserID,String admissionCoPassword) throws SQLException;

    public boolean isValidUserUsername(String admissionCoUserID, String admissionCoPassword) throws SQLException;

    public boolean resetUsername(String admissionCoUserID,String admissionCoUsername) throws SQLException;
}
