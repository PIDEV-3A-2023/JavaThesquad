module com.example.gestionvente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires stripe.java;
    requires java.mail;
    exports com.example.gestionvente.healtConnect;


    opens com.example.gestionvente.entites to javafx.base;
    opens com.example.gestionvente to javafx.fxml;
    exports com.example.gestionvente;
    exports com.example.gestionvente.gui;
    opens com.example.gestionvente.gui.shared to javafx.fxml;
    opens com.example.gestionvente.gui to javafx.fxml;
    exports com.example.gestionvente.gui.shared;
}