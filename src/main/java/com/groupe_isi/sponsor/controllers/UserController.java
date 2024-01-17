package com.groupe_isi.sponsor.controllers;

import com.groupe_isi.sponsor.config.Db;
import com.groupe_isi.sponsor.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    @FXML
    private Button connexionBtn;

    @FXML
    private TextField loginFld;

    @FXML
    private PasswordField passwordFld;

    @FXML
    void getLogin(ActionEvent event) throws IOException {
        String username = loginFld.getText();
        String password = passwordFld.getText();

        boolean isAuthenticated = authenticateUser(username, password);

        if (isAuthenticated) {
            System.out.println("Connexion réussie !");
            String userRole = getUserRole(username, password);
            if ("ROLE_ADMIN".equals(userRole)) {
                Outils.load(event, "Dashboard", "page/admin/dashboard.fxml");
            } else if ("ROLE_CANDIDAT".equals(userRole)) {
                Outils.load(event, "Candidat Page", "page/candidat/candidat.fxml");
            } else if ("ROLE_ELECTEUR".equals(userRole)) {
                Outils.load(event, "Electeur Page", "page/electeur/electeur.fxml");
            } else {
                System.out.println("Role non reconnu");
            }
        } else {
            System.out.println("Échec de la connexion.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        try (Connection connection = Db.getConnection()) {
            String query = "SELECT * FROM user WHERE login = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getUserRole(String username, String password) {
        try (Connection connection = Db.getConnection()) {
            String query = "SELECT role.name " +
                    "FROM user " +
                    "INNER JOIN role ON user.profil = role.id " +
                    "WHERE user.login = ? AND user.password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
