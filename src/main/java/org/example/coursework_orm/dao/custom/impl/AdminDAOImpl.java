package org.example.coursework_orm.dao.custom.impl;

import org.example.coursework_orm.config.FactoryConfiguration;
import org.example.coursework_orm.dao.custom.AdminDAO;
import org.example.coursework_orm.entity.Admin;
import org.example.coursework_orm.entity.Admission_Coordinator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean add(Admin entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Admin entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Admin entity) throws SQLException {
        return false;
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean saveAdmin(String adminUserID, String adminUsername, String adminPassword) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Admin admin = new Admin();

            admin.setAdminUserID(adminUserID);
            admin.setAdminUsername(adminUsername);
            admin.setAdminPassword(adminPassword);

            session.save(admin);
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

        List<String> idList = session.createQuery("SELECT a.adminUserID FROM org.example.coursework_orm.entity.Admin a", String.class).list();

        transaction.commit();
        session.close();

        return idList;
    }

    @Override
    public String getCurrentAdminID() throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();



        String hql = "SELECT a.adminUserID FROM org.example.coursework_orm.entity.Admin a ORDER BY a.adminUserID DESC";


        Query<String> query = session.createQuery(hql, String.class);
        query.setMaxResults(1);

        String oid = query.uniqueResult();

        transaction.commit();
        session.close();


        return oid;
    }
}
