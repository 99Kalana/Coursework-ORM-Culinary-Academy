package org.example.coursework_orm.dao.custom.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.coursework_orm.config.FactoryConfiguration;
import org.example.coursework_orm.controller.HomepageAdminController;
import org.example.coursework_orm.controller.HomepageAdmissionCoordinatorController;
import org.example.coursework_orm.dao.custom.AdmissionCoordinatorSignInDAO;
import org.example.coursework_orm.entity.Admin;
import org.example.coursework_orm.entity.AdmissionCoordinatorSignIn;
import org.example.coursework_orm.entity.Admission_Coordinator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdmissionCoordinatorSignInDAOImpl implements AdmissionCoordinatorSignInDAO {
    @Override
    public boolean add(AdmissionCoordinatorSignIn entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(AdmissionCoordinatorSignIn entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(AdmissionCoordinatorSignIn entity) throws SQLException {
        return false;
    }

    @Override
    public List<AdmissionCoordinatorSignIn> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public void checkCredential(String admissionCoUsername, String admissionCoPassword, AnchorPane root) throws SQLException, IOException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // HQL query to find the Admin by adminUsername
            Query<Admission_Coordinator> query = session.createQuery("FROM  org.example.coursework_orm.entity.Admission_Coordinator ac WHERE ac.admissionCoUsername = :admissionCoUsername", Admission_Coordinator.class);
            query.setParameter("admissionCoUsername", admissionCoUsername);

            Admission_Coordinator admissionCoordinator = query.uniqueResult();

            if (admissionCoordinator != null) {
                String dbPassword = admissionCoordinator.getAdmissionCoPassword();

                // Assuming you're using BCrypt, you would compare with BCrypt
                if (BCrypt.checkpw(admissionCoPassword, dbPassword)) {
                    navigateToAdmissionCoordinatorHomepage(admissionCoUsername,root);
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

    private void navigateToAdmissionCoordinatorHomepage(String admissionCoUsername, AnchorPane root) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/homepage_admission_coordinator.fxml"));
        Parent admissionCoordinatorHomePage = loader.load();


        HomepageAdmissionCoordinatorController controller = loader.getController();
        controller.setAdmissionCoordinatorUsername(admissionCoUsername);


        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(admissionCoordinatorHomePage));
        stage.setTitle("Admission Coordinator Homepage");
        stage.centerOnScreen();
    }


}
