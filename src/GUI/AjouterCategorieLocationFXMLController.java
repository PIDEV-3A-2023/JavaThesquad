/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategorieLocation;
import Services.CategorieLocationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class AjouterCategorieLocationFXMLController implements Initializable {
      @FXML
    private AnchorPane anchor;

    @FXML
    private TextArea textareaDesc;

    @FXML
    private Label labelnom;

    @FXML
    private TextField txtfieldNom;

    @FXML
    private Label labeldescription;

    @FXML
    private Button btn;

    @FXML
    private Label titrecatajout;
    @FXML
    private Button revenir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
private void ajoutCategorielocation(ActionEvent event) {
    String nom = txtfieldNom.getText();
    String description = textareaDesc.getText();
    
    if (nom.isEmpty() || description.isEmpty()) {
        // Afficher un message d'erreur si les champs sont vides
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.show();
    } else if (description.length() <= 7) {
        // Afficher un message d'erreur si la description est trop courte
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La description doit contenir au moins 8 caractères");
        alert.show();
    } else {
        // Ajouter la catégorie si les champs sont remplis et la description est valide
        CategorieLocation c = new CategorieLocation(nom, description);
        CategorieLocationService service = new CategorieLocationService();
        try {
            service.ajoutCategorielocation(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("catégorie ajoutée avec succes");
            alert.show();

            txtfieldNom.setText("");
            textareaDesc.setText("");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
          

        
@FXML
    private void redirigerliste(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieLocationFXML.fxml"));
    Parent root;
    try {
    root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
}
