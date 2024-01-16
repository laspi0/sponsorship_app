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
        try (Connection connection = Db.getConnection()) {
            String query = "SELECT * FROM user WHERE profil IS NULL OR profil != 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void deactivateUser(User user) {
        try (Connection connection = Db.getConnection()) {
            String query = "UPDATE user SET actived = 0 WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
