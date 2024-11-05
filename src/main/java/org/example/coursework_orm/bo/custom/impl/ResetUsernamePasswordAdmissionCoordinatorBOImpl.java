package org.example.coursework_orm.bo.custom.impl;

import org.example.coursework_orm.bo.custom.ResetUsernamePasswordAdmissionCoordinatorBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.example.coursework_orm.dao.custom.ResetUsernamePasswordAdminDAO;
import org.example.coursework_orm.dao.custom.ResetUsernamePasswordAdmissionCoordinatorDAO;

import java.sql.SQLException;

public class ResetUsernamePasswordAdmissionCoordinatorBOImpl implements ResetUsernamePasswordAdmissionCoordinatorBO {

    ResetUsernamePasswordAdmissionCoordinatorDAO resetUsernamePasswordAdmissionCoordinatorDAO = (ResetUsernamePasswordAdmissionCoordinatorDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RESET_USERNAME_PASSWORD_ADMISSION_COORDINATOR);


    public boolean isValidUserPassword(String admissionCoUserID, String admissionCoUsername) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorDAO.isValidUserPassword(admissionCoUserID,admissionCoUsername);

    }

    public boolean resetPassword(String admissionCoUserID,String admissionCoPassword) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorDAO.resetPassword(admissionCoUserID,admissionCoPassword);

    }

    public boolean isValidUserUsername(String admissionCoUserID, String admissionCoPassword) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorDAO.isValidUserUsername(admissionCoUserID,admissionCoPassword);

    }

    public boolean resetUsername(String admissionCoUserID,String admissionCoUsername) throws SQLException {

        return resetUsernamePasswordAdmissionCoordinatorDAO.resetUsername(admissionCoUserID,admissionCoUsername);

    }

}
