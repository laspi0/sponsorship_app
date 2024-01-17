package com.groupe_isi.sponsor.controllers;

import com.groupe_isi.sponsor.repo.ParrainerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Map;

public class ParrainerListController {

    @FXML
    private ListView<String> parrainagesListView;

    private ParrainerRepository parrainerRepository = new ParrainerRepository();

    @FXML
    private void initialize() {
        loadParrainages();
    }

    private void loadParrainages() {
        Map<String, Integer> parrainagesMap = parrainerRepository.getNumberOfParrainagesPerCandidatWithNames();

        ObservableList<String> parrainagesList = FXCollections.observableArrayList();
        parrainagesMap.forEach((candidatName, nombreParrainages) ->
                parrainagesList.add(candidatName + ": " + nombreParrainages + " parrainages"));

        parrainagesListView.setItems(parrainagesList);
    }
}
