package org.example.coursework_orm.bo.custom.impl;

import javafx.scene.layout.AnchorPane;

import org.example.coursework_orm.bo.custom.AdmissionCoordinatorSignInBO;
import org.example.coursework_orm.dao.DAOFactory;
import org.example.coursework_orm.dao.custom.AdmissionCoordinatorSignInDAO;

import java.io.IOException;
import java.sql.SQLException;

public class AdmissionCoordinatorSignInBOImpl implements AdmissionCoordinatorSignInBO {
    AdmissionCoordinatorSignInDAO admissionCoordinatorSignInDAO = (AdmissionCoordinatorSignInDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ADMISSION_COORDINATOR_SIGN_IN);

    public void checkCredential(String admissionCoUsername,String admissionCoPassword,AnchorPane root) throws SQLException, IOException {
        admissionCoordinatorSignInDAO.checkCredential(admissionCoUsername,admissionCoPassword,root);
    }
}
