/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entites.Evenement;
import entites.Participation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.EvenementService;
import services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AffichageEvenementFrontController implements Initializable {

    @FXML
    private AnchorPane intEvent;
    @FXML
    private TableView<Evenement> listeE;
    private TableColumn<Evenement, String> nomCol;
    private TableColumn<Evenement, String> typeCol;
    private TableColumn<Evenement, String> adresseCol;
    private TableColumn<Evenement, String> descriptionCol;
    private TableColumn<Evenement, Date> dateCol;
    private TableColumn<Evenement, Void> participerCol;
    //private TableColumn<Evenement, Void> modifierCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomCol = new TableColumn<>("Nom");
        typeCol = new TableColumn<>("Type");
        adresseCol = new TableColumn<>("Adresse");
        descriptionCol = new TableColumn<>("Description");
        dateCol = new TableColumn<>("Date");
        participerCol = new TableColumn<>("");
        
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
       
      
     
      
        participerCol.setCellFactory(evenement -> new TableCell<Evenement, Void>() {
     private final Button btn = new Button("Participer");
     private boolean participe = false; // Initialise la variable participe à false

     {
         btn.setOnAction((ActionEvent event) -> {
             /*Evenement evenement = getTableView().getItems().get(getIndex());
             ParticipationService service = new ParticipationService();
             Participation p = new Participation(evenement.getId(), 1);
             try {
                 if (participe) { // Si l'utilisateur a déjà participé, supprime la participation
                     System.out.println(p.getEvenementId());
                     service.supprimerParticipation(7);

                     btn.setText("Participer"); // Met à jour le texte du bouton
                     participe = false; // Met à jour la variable participe
                 } else { // Sinon, ajoute une participation
                     service.ajoutParticipation(p);

                     btn.setText("S'abstenir"); // Met à jour le texte du bouton
                     participe = true; // Met à jour la variable participe
                 }

             } catch (SQLException ex) {
                 Logger.getLogger(AffichageEvenementFrontController.class.getName()).log(Level.SEVERE, null, ex);
             }*/
             
        Evenement evenement = getTableView().getItems().get(getIndex());
        ParticipationService service = new ParticipationService();
        Participation p = null;
             try {
                 p = service.getParticipationByEvenementIdAndUserId(evenement.getId(), 1); // Récupère la participation de l'utilisateur pour cet événement
             } catch (SQLException ex) {
                 Logger.getLogger(AffichageEvenementFrontController.class.getName()).log(Level.SEVERE, null, ex);
             }
        try {
            if (p != null) { // Si l'utilisateur a déjà participé, supprime la participation
                service.supprimerParticipation(p.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression de la participation");
                alert.setHeaderText(null);
                alert.setContentText("Votre participation a été supprimé avec succès");
                alert.showAndWait();
                btn.setText("Participer"); // Met à jour le texte du bouton
                participe = false; // Met à jour la variable participe
            } else { // Sinon, ajoute une participation
                p = new Participation(evenement.getId(), 1);
                service.ajoutParticipation(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation de la participation");
                alert.setHeaderText(null);
                alert.setContentText("Votre participation a été effectué avec succès");
                alert.showAndWait();
                btn.setText("Participer"); // Met à jour le texte du bouton
                btn.setText("S'abstenir"); // Met à jour le texte du bouton
                participe = true; // Met à jour la variable participe
            }
             p = null;

        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvenementFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

         });
     }

     
     
     
     @Override
     public void updateItem(Void item, boolean empty) {
         super.updateItem(item, empty);
         if (empty) {
             setGraphic(null);
         } else {
             setGraphic(btn);
             Evenement evenement = getTableView().getItems().get(getIndex());
             ParticipationService service = new ParticipationService();
             Participation p = new Participation(evenement.getId(), 1);
             if (participe) {
                 btn.setText("S'abstenir");
             } else {
                 btn.setText("Participer");
             }
         }
     }
 });
      
      
        

        listeE.getColumns().addAll(nomCol, typeCol, adresseCol, descriptionCol, dateCol, participerCol);

        EvenementService service = new EvenementService();
        List<Evenement> evenements = null;
        try {
            evenements = service.afficherListeEvenement();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvenementFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        listeE.getItems().addAll(evenements);

        }    
}
