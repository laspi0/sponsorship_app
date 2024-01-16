package com.groupe_isi.sponsor.controllers;

import com.groupe_isi.sponsor.config.Db;
import com.groupe_isi.sponsor.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        // Récupérer les informations de connexion depuis les champs de texte
        String username = loginFld.getText();
        String password = passwordFld.getText();
        Outils.load(event,"Dashboard","page/admin/dashboard.fxml");
//        Outils.load(event,"Dashboard","page/admin/candidat.fxml");

        boolean isConnected = authenticateUser(username, password);

        if (isConnected) {
            System.out.println("Connexion réussie !");

        } else {
            System.out.println("Échec de la connexion.");
        }
    }

    // Méthode pour authentifier l'utilisateur en se connectant à la base de données
    private boolean authenticateUser(String username, String password) {
        try (Connection connection = Db.getConnection()) {
            String query = "SELECT * FROM user WHERE login = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Si une ligne est renvoyée, l'utilisateur est authentifié
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
