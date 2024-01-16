module com.groupe_isi.sponsor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


                            
    opens com.groupe_isi.sponsor to javafx.fxml;
    exports com.groupe_isi.sponsor;
    exports com.groupe_isi.sponsor.controllers;
    opens com.groupe_isi.sponsor.controllers to javafx.fxml;
    exports com.groupe_isi.sponsor.config;
    opens com.groupe_isi.sponsor.config to javafx.fxml;
    exports com.groupe_isi.sponsor.entity;
    opens com.groupe_isi.sponsor.entity to javafx.fxml;
    exports com.groupe_isi.sponsor.repo;
    opens com.groupe_isi.sponsor.repo to javafx.fxml;
}