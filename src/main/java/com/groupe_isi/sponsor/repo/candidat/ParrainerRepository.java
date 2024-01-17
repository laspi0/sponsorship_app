package com.groupe_isi.sponsor.repo.candidat;// Dans la classe ParrainerRepository

import com.groupe_isi.sponsor.config.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParrainerRepository {


    public List<String> getElecteursParrainesByCandidatConnecte() {
        List<String> electeursParraines = new ArrayList<>();

        try (Connection connection = Db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.nom, u.prenom FROM user u " +
                             "JOIN parrainer p ON u.id = p.electeur " +
                             "WHERE p.candidat = ?")) {

            preparedStatement.setInt(1, getCandidatConnecteId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String electeurNom = resultSet.getString("nom");
                    String electeurPrenom = resultSet.getString("prenom");
                    electeursParraines.add(electeurNom + " " + electeurPrenom);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return electeursParraines;
    }

    private int getCandidatConnecteId() {
        return 1;
    }
}
