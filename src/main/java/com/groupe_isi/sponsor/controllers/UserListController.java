package com.groupe_isi.sponsor.controllers;

import com.groupe_isi.sponsor.entity.User;
import com.groupe_isi.sponsor.repo.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UserListController {

    @FXML
    private TableView<User> userTableView;

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

    private void loadUsers() {
        List<User> users = userRepository.getAllUsers();
        userTableView.getItems().setAll(users);
    }

    @FXML
    private void createUser() {
        // Récupérer les valeurs des champs
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String login = loginField.getText();
        String password = passwordField.getText();

        // Créer un nouvel utilisateur
        User newUser = new User(0, nom, prenom, login, password, 1, null); // Assurez-vous d'ajuster les valeurs par défaut

        // Ajouter l'utilisateur à la base de données
        userRepository.createUser(newUser);

        // Recharger la liste des utilisateurs
        loadUsers();

        // Effacer les champs du formulaire
        clearForm();
    }



    private void clearForm() {
        nomField.clear();
        prenomField.clear();
        loginField.clear();
        passwordField.clear();
    }
}
