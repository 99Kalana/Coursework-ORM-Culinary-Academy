package org.example.coursework_orm.dao.custom.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.coursework_orm.config.FactoryConfiguration;
import org.example.coursework_orm.controller.HomepageAdminController;
import org.example.coursework_orm.dao.custom.AdminSignInDAO;
import org.example.coursework_orm.entity.Admin;
import org.example.coursework_orm.entity.AdminSignIn;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminSignInDAOImpl implements AdminSignInDAO {
    @Override
    public boolean add(AdminSignIn entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(AdminSignIn entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(AdminSignIn entity) throws SQLException {
        return false;
    }

    @Override
    public List<AdminSignIn> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public void checkCredential(String adminUsername, String adminPassword, AnchorPane root) throws SQLException, IOException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // HQL query to find the Admin by adminUsername
            Query<Admin> query = session.createQuery("FROM  org.example.coursework_orm.entity.Admin a WHERE a.adminUsername = :adminUsername", Admin.class);
            query.setParameter("adminUsername", adminUsername);

            Admin admin = query.uniqueResult();

            if (admin != null) {
                String dbPassword = admin.getAdminPassword();

                // Assuming you're using BCrypt, you would compare with BCrypt
                if (BCrypt.checkpw(adminPassword, dbPassword)) {
                    navigateToAdminHomepage(adminUsername,root);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Sorry! Entered password is incorrect!").show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Sorry! Entered user ID can't be found!").show();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    private void navigateToAdminHomepage(String adminUsername,AnchorPane root) throws IOException {
        // Use FXMLLoader to load the FXML and get the controller instance
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/homepage_admin.fxml"));
        Parent adminHomePage = loader.load();

        // Get the controller from the loader and pass the username
        HomepageAdminController controller = loader.getController();
        controller.setAdminUsername(adminUsername);

        // Get the current stage from the provided root AnchorPane
        Stage stage = (Stage) root.getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(adminHomePage));
        stage.setTitle("Admin Homepage");
        stage.centerOnScreen();
    }
}
