package com.groupe_isi.sponsor.controllers;

import com.groupe_isi.sponsor.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button candidatBtn;




    @FXML
    private Button electeurBtn;

    @FXML
    private Button parrainageBtn;

    @FXML
    private BorderPane borderpane1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Code d'initialisation ici, si nécessaire
    }

    @FXML
    void getCandidat(ActionEvent event) throws IOException {

        URL url = getClass().getResource("candidat.fxml");
        AnchorPane view = FXMLLoader.load(url);
                borderpane1.setCenter(view);
    }

    @FXML
    void getElecteur(ActionEvent event) throws IOException {
        URL  location =getClass().getResource("page/admin/candidat.fxml");
        if (location != null) {
            System.out.println("je suis la");
        }
//        AnchorPane view = FXMLLoader.load(getClass().getResource("page/admin/candidat.fxml"));
//        borderpane1.setCenter(view);
    }

    @FXML
    void getParrains(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("page/admin/candidat.fxml"));
        borderpane1.setCenter(view);
    }
}
