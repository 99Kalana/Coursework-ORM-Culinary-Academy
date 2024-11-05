package org.example.coursework_orm.bo.custom.impl;

import javafx.scene.layout.AnchorPane;
import org.example.coursework_orm.bo.custom.AdminSignInBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.example.coursework_orm.dao.custom.AdminSignInDAO;

import java.io.IOException;
import java.sql.SQLException;

public class AdminSignInBOImpl implements AdminSignInBO {

    AdminSignInDAO adminSignInDAO = (AdminSignInDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ADMIN_SIGN_IN);

    public void checkCredential(String adminUsername,String adminPassword,AnchorPane root) throws SQLException, IOException {
        adminSignInDAO.checkCredential(adminUsername,adminPassword,root);
    }

}
