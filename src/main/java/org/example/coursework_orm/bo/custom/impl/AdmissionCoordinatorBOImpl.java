package org.example.coursework_orm.bo.custom.impl;

import org.example.coursework_orm.bo.custom.AdmissionCoordinatorBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.example.coursework_orm.dao.custom.AdmissionCoordinatorDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdmissionCoordinatorBOImpl implements AdmissionCoordinatorBO {

    AdmissionCoordinatorDAO admissionCoordinatorDAO = (AdmissionCoordinatorDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ADMISSION_COORDINATOR);


    public boolean saveAdmissionCoordinator(String admissionCoUserID,String admissionCoUsername,String admissionCoPassword)throws SQLException{
        return admissionCoordinatorDAO.saveAdmissionCoordinator(admissionCoUserID,admissionCoUsername,admissionCoPassword);
    }

    public List<String> getIds() throws SQLException {
        List<String> idList = new ArrayList<>();
        List<String> userIDs = admissionCoordinatorDAO.getIds();

        for (String id : userIDs) {
            idList.add(id);
        }

        return idList;
    }

    public  String getCurrentAdmissionCoordinatorID() throws SQLException {

        return  admissionCoordinatorDAO.getCurrentAdmissionCoordinatorID();

    }

}
