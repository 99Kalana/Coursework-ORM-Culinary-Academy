package org.example.coursework_orm.dao.custom;

import org.example.coursework_orm.dao.CrudDAO;
import org.example.coursework_orm.entity.ResetUsernamePasswordAdmissionCoordinator;

import java.sql.SQLException;

public interface ResetUsernamePasswordAdmissionCoordinatorDAO extends CrudDAO<ResetUsernamePasswordAdmissionCoordinator> {
    public boolean isValidUserPassword(String admissionCoUserID, String admissionCoUsername) throws SQLException;

    public boolean resetPassword(String admissionCoUserID,String admissionCoPassword) throws SQLException;

    public boolean isValidUserUsername(String admissionCoUserID, String admissionCoPassword) throws SQLException;

    public boolean resetUsername(String admissionCoUserID,String admissionCoUsername) throws SQLException;
}
