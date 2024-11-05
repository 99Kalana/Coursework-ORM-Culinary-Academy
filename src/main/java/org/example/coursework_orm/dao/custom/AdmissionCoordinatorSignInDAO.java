package org.example.coursework_orm.dao.custom;

import javafx.scene.layout.AnchorPane;
import org.example.coursework_orm.dao.CrudDAO;
import org.example.coursework_orm.entity.AdmissionCoordinatorSignIn;

import java.io.IOException;
import java.sql.SQLException;

public interface AdmissionCoordinatorSignInDAO extends CrudDAO<AdmissionCoordinatorSignIn> {
    public void checkCredential(String admissionCoUsername, String admissionCoPassword, AnchorPane root) throws SQLException, IOException;
}
