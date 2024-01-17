package com.groupe_isi.sponsor.repo;

import com.groupe_isi.sponsor.config.Db;
import com.groupe_isi.sponsor.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElecteurRepository {

    public List<User> getElecteurs() {
        List<User> electeurList = new ArrayList<>();
        try (Connection connection = Db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE profil = 3");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int actived = resultSet.getInt("actived");

                User electeur = new User(id, nom, prenom, login, password, actived, 3);
                electeurList.add(electeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return electeurList;
    }
}
