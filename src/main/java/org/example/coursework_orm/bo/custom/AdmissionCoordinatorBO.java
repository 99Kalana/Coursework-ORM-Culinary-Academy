package org.example.coursework_orm.bo.custom;

import org.example.coursework_orm.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AdmissionCoordinatorBO extends SuperBO {

    public boolean saveAdmissionCoordinator(String admissionCoUserID,String admissionCoUsername,String admissionCoPassword) throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentAdmissionCoordinatorID() throws SQLException;

}
