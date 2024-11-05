package org.example.coursework_orm.bo.custom.impl;

import org.example.coursework_orm.bo.custom.AdminBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.example.coursework_orm.dao.custom.AdminDAO;
import org.example.coursework_orm.dao.custom.AdmissionCoordinatorDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ADMIN);


    public boolean saveAdmin(String adminUserID,String adminUsername,String adminPassword)throws SQLException {
        return adminDAO.saveAdmin(adminUserID,adminUsername,adminPassword);
    }

    public List<String> getIds() throws SQLException {
        List<String> idList = new ArrayList<>();
        List<String> userIDs = adminDAO.getIds();

        for (String id : userIDs) {
            idList.add(id);
        }

        return idList;
    }

    public  String getCurrentAdminID() throws SQLException {

        return  adminDAO.getCurrentAdminID();

    }

}
