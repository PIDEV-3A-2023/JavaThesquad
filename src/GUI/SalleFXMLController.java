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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.SalleService;

/**
 *
 * @author LENOVO
 */
public class SalleFXMLController implements Initializable{
  @FXML
    private TableColumn<Salle, Integer> adc;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private TableColumn<Salle,Integer> ida;

    @FXML
    private Label listes;

    @FXML
    private TableColumn<Salle, String> nomac;

    @FXML
    private TableColumn<Salle, Integer> numac;

    @FXML
    private TableColumn<Salle, Academie> spac;

    @FXML
    private TableView<Salle> tables;

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      afficherListesalles();
    }
     @FXML
private void afficherListesalles() {
    SalleService ss = new SalleService();
    try {
      
        ObservableList<Salle> salles = FXCollections.observableArrayList(ss.afficherListesalles());
ida.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));
      
nomac.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));
       adc.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getCapacite())));
       numac.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getEquipement())));
     spac.setCellValueFactory(cellData ->
    new SimpleObjectProperty(cellData.getValue().getAcademie().getNom()));

        // Afficher les données dans le TableView
        tables.setItems(salles);
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Afficher une alerte en cas d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'affichage de la liste des salles.");
        alert.show();
    }
}

public void supprimer(ActionEvent e){
        SalleService ss = new SalleService();
        ss.supprimersalle(tables.getSelectionModel().getSelectedItem().getId());
        afficherListesalles();
    }

public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutsalleFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            AjoutsalleFXMLController a = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchor.getChildren().clear();
        anchor.getChildren().add(newLoadedPane);
       

    }
public void redirecttomodif(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifiersalleFXML.fxml"));
         
          
            newLoadedPane = loader.load();
            ModifiersalleFXMLController m = loader.getController();
            m.pass(tables.getSelectionModel().getSelectedItem());
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchor.getChildren().clear();
        anchor.getChildren().add(newLoadedPane);
       

    }

}
