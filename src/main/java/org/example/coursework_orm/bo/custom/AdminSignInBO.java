package org.example.coursework_orm.bo.custom;

import javafx.scene.layout.AnchorPane;
import org.example.coursework_orm.bo.SuperBO;

import java.io.IOException;
import java.sql.SQLException;

public interface AdminSignInBO extends SuperBO {
    public void checkCredential(String adminUsername, String adminPassword, AnchorPane root) throws SQLException, IOException;
}
