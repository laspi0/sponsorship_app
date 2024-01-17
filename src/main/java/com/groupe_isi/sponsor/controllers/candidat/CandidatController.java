package com.groupe_isi.sponsor.controllers.candidat;// Dans la classe ParrainerListController

import com.groupe_isi.sponsor.repo.candidat.ParrainerRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class CandidatController {

    @FXML
    private ListView<String> electeursParrainesListView;

    private ParrainerRepository parrainerRepository = new ParrainerRepository();

    @FXML
    private void initialize() {
        loadElecteursParraines();
    }

    private void loadElecteursParraines() {
        List<String> electeursParraines = parrainerRepository.getElecteursParrainesByCandidatConnecte();
        electeursParrainesListView.getItems().setAll(electeursParraines);
    }
}
