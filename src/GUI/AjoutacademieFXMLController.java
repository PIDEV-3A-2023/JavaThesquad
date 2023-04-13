/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Academie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import services.AcademieService;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutacademieFXMLController implements Initializable {
    
     @FXML
    private Label aclabel;

    @FXML
    private Label ad;

    @FXML
    private TextField adTF;

    @FXML
    private AnchorPane anch;

    @FXML
    private Button btn;

    @FXML
    private Label no;

    @FXML
    private TextField nomTF;

    @FXML
    private Label nt;

    @FXML
    private TextField numtelTF;
 
    @FXML
    private Label sp;

    @FXML
    private TextField spTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
private void ajouterAcademie(ActionEvent event) {
    // Récupérer les valeurs des champs de texte
    String nom = nomTF.getText();
    String adresse = adTF.getText();
    String numtel = numtelTF.getText();
    String specialite = spTF.getText();

    // Vérifier que les champs ne sont pas vides
    if (nom.isEmpty() || adresse.isEmpty() || numtel.isEmpty() || specialite.isEmpty()) {
        // Afficher une alerte si l'un des champs est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.show();
        return;
    }

    // Vérifier que le numéro de téléphone est composé de 8 chiffres
    if (!numtel.matches("\\d{8}")) {
        // Afficher une alerte si le numéro de téléphone est invalide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres.");
        alert.show();
        return;
    }

    // Si tout est valide, ajouter l'académie
    Academie aa = new Academie(nom, adresse, numtel, specialite);
    AcademieService as = new AcademieService();
    try {
        as.ajoutAcademiee(aa);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("Academie ajoutée avec succes");
        alert.show();

        nomTF.setText("");
        adTF.setText("");
        numtelTF.setText("");
        spTF.setText("");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
}
