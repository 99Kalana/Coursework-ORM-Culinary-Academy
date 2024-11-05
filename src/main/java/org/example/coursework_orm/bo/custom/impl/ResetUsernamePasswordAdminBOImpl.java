package org.example.coursework_orm.bo.custom.impl;

import org.example.coursework_orm.bo.custom.ResetUsernamePasswordAdminBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.example.coursework_orm.dao.custom.ResetUsernamePasswordAdminDAO;

import java.sql.SQLException;

public class ResetUsernamePasswordAdminBOImpl implements ResetUsernamePasswordAdminBO {

    ResetUsernamePasswordAdminDAO resetUsernamePasswordAdminDAO = (ResetUsernamePasswordAdminDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RESET_USERNAME_PASSWORD_ADMIN);



    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException {

        return resetUsernamePasswordAdminDAO.isValidUserPassword(adminUserID,adminUsername);

    }

    public boolean resetPassword(String adminUserID,String adminPassword) throws SQLException {

        return resetUsernamePasswordAdminDAO.resetPassword(adminUserID, adminPassword);

    }

    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException {

        return resetUsernamePasswordAdminDAO.isValidUserUsername(adminUserID,adminPassword);

    }

    public boolean resetUsername(String adminUserID,String adminUsername) throws SQLException {

        return resetUsernamePasswordAdminDAO.resetUsername(adminUserID, adminUsername);

    }

}
