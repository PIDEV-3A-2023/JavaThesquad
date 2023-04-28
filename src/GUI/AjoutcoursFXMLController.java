/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Cours;
import entities.Salle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.CoursService;
import services.SalleService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutcoursFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button ajouuut;

    @FXML
    private AnchorPane annchr;

    @FXML
    private ComboBox<Salle> combosalle;

    @FXML
    private Label datelabel;

    @FXML
    private DatePicker datepick;

    @FXML
    private Label dureelabel;

    @FXML
    private TextField dureetext;

    @FXML
    private Label nblabel;

    @FXML
    private TextField nbpartitext;

    @FXML
    private TextField nomcours;

    @FXML
    private Label nomlabel;

    @FXML
    private Label sallelabel;
  @FXML
    private Button rt;


    Cours c=new Cours();
    CoursService cs = new CoursService();
    SalleService ss = new SalleService();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
       combosalle.getItems().addAll(ss.afficherListesalles());
       } catch(SQLException e){
           e.printStackTrace();
       }
    }
      @FXML
    private void ajou(ActionEvent event) {
        LocalDate localDate = datepick.getValue();
    if (localDate == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    Date date = Date.from(instant);
     if (nomcours.getText().isEmpty() || dureetext.getText().isEmpty() || nbpartitext.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    if (combosalle.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir la salle.");
        alert.showAndWait();
        return;
    }
  // Vérifier que la date est supérieure ou égale à la date actuelle
    if (localDate.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date incorrecte");
        alert.setHeaderText(null);
        alert.setContentText("La date doit être supérieure ou égale à la date actuelle.");
        alert.showAndWait();
        return;
    } 
    else {
        String nom=nomcours.getText();
        LocalDate dateSaisie = datepick.getValue();
        date = java.sql.Date.valueOf(datepick.getValue());
        Integer duree=Integer.parseInt(dureetext.getText());
        Integer nbparticipants=Integer.parseInt(nbpartitext.getText());
        Salle salle=combosalle.getValue();
    if (duree > 2) {
 Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Duree incorrect");
        alert.setHeaderText(null);
        alert.setContentText("La durée de cours ne doit pas dépasser 2 heures.");
        alert.showAndWait();
return;
}

     Cours cours = new Cours(nomcours.getText(), (java.sql.Date) date, Integer.parseInt(dureetext.getText()), Integer.parseInt(nbpartitext.getText()), combosalle.getValue());
    try {
        cs.ajoutCours(cours);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Cours ajouté avec succès");
        alert.show();

        nomcours.setText("");
        datepick.setValue(null);
        dureetext.setText("");
        nbpartitext.setText("");
    
        combosalle.setValue(null);
    } catch (SQLException ex) {
      Logger.getLogger(AjoutcoursFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    
}
    
    @FXML
public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CoursFXML.fxml"));
         
            
            newLoadedPane = loader.load();
           CoursFXMLController cc = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        annchr.getChildren().clear();
        annchr.getChildren().add(newLoadedPane);
       

    } 
    
    
}
