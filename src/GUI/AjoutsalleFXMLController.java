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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.AcademieService;
import services.SalleService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutsalleFXMLController implements Initializable {
  @FXML
    private TextField TFCAPACITE;

    @FXML
    private TextField TFEQUIP;

    @FXML
    private TextField TFNOM;

    @FXML
    private Label academie;

    @FXML
    private Label aclabel;

    @FXML
    private Button ajout;

    @FXML
    private AnchorPane anchhr;

    @FXML
    private Label capacite;

    @FXML
    private ComboBox<Academie> comboac;

    @FXML
    private Label equipement;

    @FXML
    private Label nom;

    @FXML
    private Button revenir;
   

    /**
     * Initializes the controller class.
     */
     SalleService ss=new SalleService();
     AcademieService as = new AcademieService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
           comboac.getItems().addAll(as.afficherListeA());
           
       } catch (SQLException ex) {
           ex.printStackTrace();
      }

     
     
    }
   @FXML
private void ajoutSalle(ActionEvent event) throws SQLException {
    String nom = TFNOM.getText();
    int capacite = 0;
    int equipement = 0;
    Academie academie = comboac.getValue();

    // Vérifier que les champs ne sont pas vides
    if (nom.isEmpty() || academie == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
    }

    // Vérifier la capacité
    try {
        capacite = Integer.parseInt(TFCAPACITE.getText());
        if (capacite < 10 || capacite > 50) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La capacité doit être un nombre entre 10 et 50 !");
        alert.showAndWait();
        return;
    }

    // Vérifier l'équipement
    try {
        equipement = Integer.parseInt(TFEQUIP.getText());
        if (equipement > 10) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'équipement doit être un nombre inférieur ou égal à 10 !");
        alert.showAndWait();
        return;
    }

    Salle salle = new Salle(nom, capacite, equipement, academie);
    ss.ajoutSalle(salle);

   Notifications notificationBuilder = Notifications.create()
    .title("Salle ajouté avec succés ")
    .text("les ajouts sont enregistrés ")
    .hideAfter(Duration.seconds(5))
    .position(Pos.CENTER)
    .graphic(null)
    .darkStyle()
    .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // code à exécuter lorsqu'on clique sur la notification
        }
    });
notificationBuilder.showInformation(); 

    // Effacement des champs après ajout
    TFNOM.setText("");
    TFCAPACITE.setText("");
    TFEQUIP.setText("");
    comboac.setValue(null);
}

    
       /* // Afficher une alerte si l'un des champs est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.show();
        return;
    } else {
   
    // Si tout est valide, ajouter salle
 
    Salle s = new Salle(nom, capacite, equipement, academie);
    SalleService ss = new SalleService();
    try {
        ss.ajoutSalle(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("Salle ajoutée avec succes");
        alert.show();

        TFNOM.setText("");
        TFCAPACITE.setText("");
        TFEQUIP.setText("");
        comboac.setValue(null);


    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
}*/

public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SalleFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            SalleFXMLController s = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchhr.getChildren().clear();
        anchhr.getChildren().add(newLoadedPane);
       

    }


}
