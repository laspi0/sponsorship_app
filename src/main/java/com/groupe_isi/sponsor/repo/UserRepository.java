package com.groupe_isi.sponsor.repo;

import com.groupe_isi.sponsor.config.Db;
import com.groupe_isi.sponsor.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE profil IS NULL OR profil != 1");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int actived = resultSet.getInt("actived");
                Integer profil = resultSet.getInt("profil");

                User user = new User(id, nom, prenom, login, password, actived, profil);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }

        return userList;
    }

    public void deactivateUser(User user) {
        try (Connection connection = Db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET actived = 0 WHERE id = ?")) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
    }



    public void createUser(User user) {
        try (Connection connection = Db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO user (nom, prenom, login, password, actived, profil) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getActived());
            preparedStatement.setInt(6, user.getProfil());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
    }
}
