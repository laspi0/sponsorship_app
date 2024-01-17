package com.groupe_isi.sponsor.repo;

import com.groupe_isi.sponsor.config.Db;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParrainerRepository {

    public Map<String, Integer> getNumberOfParrainagesPerCandidatWithNames() {
        Map<String, Integer> parrainagesMap = new HashMap<>();

        try (Connection connection = Db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT CONCAT(u.nom, ' ', u.prenom) AS candidat_name, COUNT(*) AS parrainages_count " +
                             "FROM user u " +
                             "JOIN parrainer p ON u.id = p.candidat " +
                             "WHERE u.profil = 2 " +
                             "GROUP BY u.id, u.nom, u.prenom");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String candidatName = resultSet.getString("candidat_name");
                int nombreParrainages = resultSet.getInt("parrainages_count");

                parrainagesMap.put(candidatName, nombreParrainages);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parrainagesMap;
    }

}
