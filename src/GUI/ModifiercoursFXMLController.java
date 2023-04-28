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
import javafx.stage.Stage;
import services.CoursService;
import services.SalleService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifiercoursFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private AnchorPane anchorr;

    @FXML
    private ComboBox<Salle> combosalle;

    @FXML
    private Label date;

    @FXML
    private DatePicker datepk;

    @FXML
    private Label duree;

    @FXML
    private TextField dureetxt;

    @FXML
    private Button modifbtn;

    @FXML
    private Label modifcours;

    @FXML
    private Label nbpartc;

    @FXML
    private Label nomlbl;

    @FXML
    private TextField nomtxt;

    @FXML
    private TextField participtxt;

    @FXML
    private Button revbtn;

    @FXML
    private Label salle;
 
    
    
        Cours cours=new Cours();
    CoursService cs = new CoursService();
    SalleService ss=new SalleService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
       combosalle.getItems().addAll(ss.afficherListesalles());
       } catch(SQLException e){
           e.printStackTrace();
       }
    }    
 
   public void pass(Cours cours){
    this.cours=cours;
    nomtxt.setText(cours.getNom());
    datepk.setValue(LocalDate.parse(cours.getDate().toString()));
    dureetxt.setText(String.valueOf(cours.getDuree())); 
    participtxt.setText(String.valueOf(cours.getNbparticipants())); 
    combosalle.setValue(cours.getSalle()); 
}
   public void Modifier(ActionEvent e) throws SQLException {
   LocalDate localDate = datepk.getValue();
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


          if (nomtxt.getText().isEmpty() || dureetxt.getText().isEmpty() || participtxt.getText().isEmpty() ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return; 
    }
           if (combosalle.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir la salle");
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
      
    cours.setNom(nomtxt.getText());
    cours.setDate(java.sql.Date.valueOf(datepk.getValue()));
    cours.setDuree(Integer.parseInt(dureetxt.getText()));
    cours.setNbparticipants(Integer.parseInt(participtxt.getText()));
    cours.setSalle(combosalle.getValue());
  
   CoursService cs = new CoursService();
        try {
            cs.modifiercours(cours);
            // Afficher une fenêtre de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous confirmer la modification?");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ModifiercoursFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    

    public void redirecttocours(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CoursFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            CoursFXMLController c = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchorr.getChildren().clear();
        anchorr.getChildren().add(newLoadedPane);
       

    }
    
}
