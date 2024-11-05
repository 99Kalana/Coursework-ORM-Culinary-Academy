package org.example.coursework_orm.bo.custom;

import javafx.scene.layout.AnchorPane;
import org.example.coursework_orm.bo.SuperBO;

import java.io.IOException;
import java.sql.SQLException;

public interface AdmissionCoordinatorSignInBO extends SuperBO {
    public void checkCredential(String admissionCoUsername, String admissionCoPassword, AnchorPane root) throws SQLException, IOException;
}
