package org.example.coursework_orm.dao.custom;

import javafx.scene.layout.AnchorPane;
import org.example.coursework_orm.dao.CrudDAO;
import org.example.coursework_orm.entity.AdminSignIn;

import java.io.IOException;
import java.sql.SQLException;

public interface AdminSignInDAO extends CrudDAO<AdminSignIn> {
    public void checkCredential(String adminUsername, String adminPassword, AnchorPane root) throws SQLException, IOException;
}
