package com.groupe_isi.sponsor;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Outils {
    private void loadPage(ActionEvent event, String title, String url) throws IOException{
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static AnchorPane load(ActionEvent event, String title, String url) throws IOException{
        new Outils().loadPage(event, title, url);
        return null;
    }
}
