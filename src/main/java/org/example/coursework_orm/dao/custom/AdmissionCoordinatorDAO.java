package org.example.coursework_orm.dao.custom;

import org.example.coursework_orm.dao.CrudDAO;
import org.example.coursework_orm.entity.Admission_Coordinator;

import java.sql.SQLException;
import java.util.List;

public interface AdmissionCoordinatorDAO extends CrudDAO<Admission_Coordinator> {

    public boolean saveAdmissionCoordinator(String admissionCoUserID,String admissionCoUsername,String admissionCoPassword) throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentAdmissionCoordinatorID() throws SQLException;

}
