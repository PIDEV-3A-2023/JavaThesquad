/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entites.Evenement;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EvenementFXMLController implements Initializable {
    @FXML
    private AnchorPane intAjout;
    @FXML
    private Label labelE;
    @FXML
    private TextField nomtf;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextField adressetf;
    @FXML
    private TextArea descriptiontf;
    @FXML
    private TextField imagetf;
 

   
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
        "Yoga",
        "Tennis",
        "Gymnastic",
        "Musculation"

        );
        comboBox.setItems(options);
    }    

   /* private void afficherE(ActionEvent event) {
           EvenementService ps = new EvenementService();
        try {
            labelE.setText(ps.afficherListeEvenement().toString());
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }*/
    
@FXML
    private void ajouterEvenement(ActionEvent event) {
           LocalDate localDate = datepicker.getValue();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        String image = imagetf.getText();
        
        if (nomtf.getText().isEmpty() || adressetf.getText().isEmpty() || descriptiontf.getText().isEmpty() || imagetf.getText().isEmpty() ||  localDate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        
         if (comboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir le type de l'evenement.");
            alert.showAndWait();
            return;
        }

        if (descriptiontf.getText().length() < 20) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Description trop courte");
            alert.setHeaderText(null);
            alert.setContentText("La description doit contenir au moins 20 caractères.");
            alert.showAndWait();
            return;
        }

         // Vérifier que l'image est au format jpg ou png
        if (!image.toLowerCase().endsWith(".jpg") && !image.toLowerCase().endsWith(".png")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("L'image doit être au format JPG ou PNG !");
            alert.showAndWait();
            return;
        }    
        
        //verifier que la date est superieure a la date actuelle
        if (localDate.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Date incorrecte");
            alert.setHeaderText(null);
            alert.setContentText("La date doit être supérieure ou égale à la date actuelle.");
            alert.showAndWait();
            return;
        }
    
    
        Evenement e = new Evenement(nomtf.getText(),comboBox.getValue().toString(),adressetf.getText(),
                    descriptiontf.getText(),imagetf.getText(),date);
            EvenementService es = new EvenementService();
            
        try {
            es.ajoutEvenement(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("Evenement ajouté avec succes");
            alert.show();
            closeWindow();
            nomtf.setText("");
            adressetf.setText("");
            descriptiontf.setText("");
            imagetf.setText("");
            datepicker.setValue(null);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chooseImageButton(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
          );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
        String imagePath = selectedFile.getAbsolutePath();
        imagetf.setText(imagePath);
        }
    }
      private void closeWindow() {
    Stage stage = (Stage) intAjout.getScene().getWindow();
    stage.close();
}

   


    
    
}

