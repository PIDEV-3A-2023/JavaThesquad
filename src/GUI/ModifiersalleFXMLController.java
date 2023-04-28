/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Academie;
import entities.Salle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.AcademieService;
import services.SalleService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifiersalleFXMLController implements Initializable {
   @FXML
    private Label acaad;

    @FXML
    private AnchorPane anchr3;

    @FXML
    private Label capacite;

    @FXML
    private TextField capacitetxt;

    @FXML
    private ComboBox<Academie> combo;

    @FXML
    private Label equipement;

    @FXML
    private TextField equtext;

    @FXML
    private Button modbtn;

    @FXML
    private Label modiff;

    @FXML
    private Label nomcc;

    @FXML
    private TextField nomtxt;
    @FXML
    private Button ret;

   /* @FXML
    void Modifier(ActionEvent event) {

    }
    
    /**
     * Initializes the controller class.
     */
    Salle salle=new Salle();
    SalleService ss = new SalleService();
    AcademieService aa=new AcademieService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
       combo.getItems().addAll(aa.afficherListeA());
       } catch(SQLException e){
           e.printStackTrace();
       }
    }    
    
   public void pass(Salle salle){
    this.salle=salle;
    nomtxt.setText(salle.getNom());
    capacitetxt.setText(String.valueOf(salle.getCapacite())); // Convertir la capacité en String
    equtext.setText(String.valueOf(salle.getEquipement())); // Convertir l'équipement en String
    combo.setValue(salle.getAcademie()); 
}
   public void Modifier(ActionEvent e) throws SQLException {
    if (nomtxt.getText().isEmpty() || capacitetxt.getText().isEmpty() || equtext.getText().isEmpty()
            || combo.getValue() == null) {
        // Afficher une alerte si l'un des champs est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    // Contrôle de saisie pour la capacité
    int capacite = 0;
    try {
        capacite = Integer.parseInt(capacitetxt.getText());
        if (capacite < 10 || capacite > 50) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException ex) {
        // Afficher une alerte si la capacité est hors des limites
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("La capacité doit être un nombre entre 10 et 50.");
        alert.showAndWait();
        return;
    }

    // Contrôle de saisie pour l'équipement
    int equipement = 0;
    try {
        equipement = Integer.parseInt(equtext.getText());
        if (equipement < 0 || equipement > 10) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException ex) {
        // Afficher une alerte si l'équipement est hors des limites
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("L'équipement doit être un nombre entre 0 et 10.");
        alert.showAndWait();
        return;
    }

    salle.setNom(nomtxt.getText());
    salle.setCapacite(capacite);
    salle.setEquipement(equipement);
    salle.setAcademie(combo.getValue());

    ss.modifiersalle(salle);

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de modification");
    alert.setHeaderText(null);
    alert.setContentText("Voulez vous confirmer la modification?");
    alert.showAndWait();
}


    public void redirecttosalle(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SalleFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            SalleFXMLController s = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchr3.getChildren().clear();
        anchr3.getChildren().add(newLoadedPane);
       

    }
}
