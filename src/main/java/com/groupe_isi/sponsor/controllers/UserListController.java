package com.groupe_isi.sponsor.controllers;

import com.groupe_isi.sponsor.entity.User;
import com.groupe_isi.sponsor.repo.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UserListController {

    @FXML
    private TableView<User> userTableView;

    @FXML
    private ComboBox<?> profileComboBox;

    @FXML
    private Button deactivateButton;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    private UserRepository userRepository = new UserRepository();


    @FXML
    private void initialize() {
        initializeTableColumns();
        loadUsers();
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

        // Ajoutez les colonnes à la TableView
        userTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, loginColumn, passwordColumn, activedColumn, profilColumn);
    }

    @FXML
    private void deactivateUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userRepository.deactivateUser(selectedUser);
            loadUsers();
        }
    }


    @FXML
    private void deleteUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userRepository.deleteUser(selectedUser);
            loadUsers();
        }
    }



    @FXML
    private void activateUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userRepository.activateUser(selectedUser);
            loadUsers();
        }
    }


    private void loadUsers() {
        List<User> users = userRepository.getAllUsers();
        userTableView.getItems().setAll(users);
    }




    @FXML
    private void createUser() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String login = loginField.getText();
        String password = passwordField.getText();

        Integer profil = profileComboBox.getSelectionModel().getSelectedItem().equals("Candidat") ? 2 : 3;

        User newUser = new User(0, nom, prenom, login, password, 1, profil);
        userRepository.createUser(newUser);
        loadUsers();
        clearForm();
    }




    private void clearForm() {
        nomField.clear();
        prenomField.clear();
        loginField.clear();
        passwordField.clear();
    }
}
