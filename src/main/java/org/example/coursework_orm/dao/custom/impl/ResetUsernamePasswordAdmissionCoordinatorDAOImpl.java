package org.example.coursework_orm.dao.custom.impl;

import org.example.coursework_orm.config.FactoryConfiguration;
import org.example.coursework_orm.dao.custom.ResetUsernamePasswordAdmissionCoordinatorDAO;
import org.example.coursework_orm.entity.ResetUsernamePasswordAdmissionCoordinator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class ResetUsernamePasswordAdmissionCoordinatorDAOImpl implements ResetUsernamePasswordAdmissionCoordinatorDAO {
    @Override
    public boolean add(ResetUsernamePasswordAdmissionCoordinator entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ResetUsernamePasswordAdmissionCoordinator entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(ResetUsernamePasswordAdmissionCoordinator entity) throws SQLException {
        return false;
    }

    @Override
    public List<ResetUsernamePasswordAdmissionCoordinator> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public boolean isValidUserPassword(String admissionCoUserID, String admissionCoUsername) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean isValid = session.createQuery("SELECT 1 FROM Admission_Coordinator ac WHERE ac.admissionCoUserID = :admissionCoUserID AND ac.admissionCoUsername = :admissionCoUsername")
                .setParameter("admissionCoUserID", admissionCoUserID)
                .setParameter("admissionCoUsername", admissionCoUsername)
                .uniqueResult() != null;

        transaction.commit();
        session.close();

        return isValid;
    }

    @Override
    public boolean resetPassword(String admissionCoUserID, String admissionCoPassword) throws SQLException {
        // Hash the new password using BCrypt
        String hashedPassword = BCrypt.hashpw(admissionCoPassword, BCrypt.gensalt());

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        int result = session.createQuery("UPDATE Admission_Coordinator ac SET ac.admissionCoPassword = :hashedPassword WHERE ac.admissionCoUserID = :admissionCoUserID")
                .setParameter("hashedPassword", hashedPassword)
                .setParameter("admissionCoUserID", admissionCoUserID)
                .executeUpdate();

        transaction.commit();
        session.close();

        return result > 0;
    }

    @Override
    public boolean isValidUserUsername(String admissionCoUserID, String admissionCoPassword) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Query to get the hashed password for the provided adminUserID
        String hql = "SELECT ac.admissionCoPassword FROM org.example.coursework_orm.entity.Admission_Coordinator ac WHERE ac.admissionCoUserID = :admissionCoUserID";
        String hashedPassword = (String) session.createQuery(hql)
                .setParameter("admissionCoUserID", admissionCoUserID)
                .uniqueResult();

        transaction.commit();
        session.close();

        // Check if the hashed password matches the provided adminPassword
        return hashedPassword != null && BCrypt.checkpw(admissionCoPassword, hashedPassword);
    }

    @Override
    public boolean resetUsername(String admissionCoUserID, String admissionCoUsername) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Query to update the username for the given adminUserID
        int result = session.createQuery("UPDATE org.example.coursework_orm.entity.Admission_Coordinator ac SET ac.admissionCoUsername = :newUsername WHERE ac.admissionCoUserID = :admissionCoUserID")
                .setParameter("newUsername", admissionCoUsername)
                .setParameter("admissionCoUserID", admissionCoUserID)
                .executeUpdate();

        transaction.commit();
        session.close();

        // Return true if the update was successful
        return result > 0;
    }
}
