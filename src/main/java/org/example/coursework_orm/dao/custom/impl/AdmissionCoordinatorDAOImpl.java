package org.example.coursework_orm.dao.custom.impl;

import org.example.coursework_orm.config.FactoryConfiguration;
import org.example.coursework_orm.dao.custom.AdmissionCoordinatorDAO;
import org.example.coursework_orm.entity.Admission_Coordinator;
import org.example.coursework_orm.entity.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class AdmissionCoordinatorDAOImpl implements AdmissionCoordinatorDAO {


    @Override
    public boolean add(Admission_Coordinator entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Admission_Coordinator entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Admission_Coordinator entity) throws SQLException {
        return false;
    }

    @Override
    public List<Admission_Coordinator> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean saveAdmissionCoordinator(String admissionCoUserID, String admissionCoUsername, String admissionCoPassword) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Admission_Coordinator admissionCoordinator = new Admission_Coordinator();

            admissionCoordinator.setAdmissionCoUserID(admissionCoUserID);
            admissionCoordinator.setAdmissionCoUsername(admissionCoUsername);
            admissionCoordinator.setAdmissionCoPassword(admissionCoPassword);

            session.save(admissionCoordinator);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<String> getIds() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<String> idList = session.createQuery("SELECT ac.admissionCoUserID FROM org.example.coursework_orm.entity.Admission_Coordinator ac", String.class).list();

        transaction.commit();
        session.close();

        return idList;
    }

    @Override
    public String getCurrentAdmissionCoordinatorID() throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();



        String hql = "SELECT ac.admissionCoUserID FROM org.example.coursework_orm.entity.Admission_Coordinator ac ORDER BY ac.admissionCoUserID DESC";


        Query<String> query = session.createQuery(hql, String.class);
        query.setMaxResults(1);

        String oid = query.uniqueResult();

        transaction.commit();
        session.close();


        return oid;
    }
}
