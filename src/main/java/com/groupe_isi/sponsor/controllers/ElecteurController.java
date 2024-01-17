package com.groupe_isi.sponsor.controllers;

import com.groupe_isi.sponsor.entity.User;
import com.groupe_isi.sponsor.repo.ElecteurRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ElecteurController {

    @FXML
    private TableView<User> electeurTableView;

    private ElecteurRepository electeurRepository = new ElecteurRepository();

    @FXML
    private void initialize() {
        initializeTableColumns();
        loadElecteurs();
    }

    private void initializeTableColumns() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User, String> prenomColumn = new TableColumn<>("Prénom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<User, String> loginColumn = new TableColumn<>("Login");
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, Integer> activedColumn = new TableColumn<>("Actived");
        activedColumn.setCellValueFactory(new PropertyValueFactory<>("actived"));

        TableColumn<User, Integer> profilColumn = new TableColumn<>("Profil");
        profilColumn.setCellValueFactory(new PropertyValueFactory<>("profil"));

        idColumn.setPrefWidth(50);
        nomColumn.setPrefWidth(100);
        prenomColumn.setPrefWidth(100);
        loginColumn.setPrefWidth(150);
        passwordColumn.setPrefWidth(150);
        activedColumn.setPrefWidth(80);
        profilColumn.setPrefWidth(80);

        electeurTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, loginColumn, passwordColumn, activedColumn, profilColumn);
    }

    private void loadElecteurs() {
        List<User> electeurs = electeurRepository.getElecteurs();
        ObservableList<User> electeursList = FXCollections.observableArrayList(electeurs);
        electeurTableView.setItems(electeursList);
    }
}
