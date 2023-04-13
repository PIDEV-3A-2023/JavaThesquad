/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.Academie;
import java.io.IOException;
import services.AcademieService;


import java.sql.SQLException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author LENOVO
 */
public class AcademieFXMLController implements Initializable {

       @FXML
    private TableColumn<Academie, String> adc;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btnajout;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private TableColumn<Academie, Integer> ida;

    @FXML
    private Label liste;

    @FXML
    private TableColumn<Academie, String> nomac;

    @FXML
    private TableColumn<Academie, String> numac;

    @FXML
    private TableColumn<Academie, String> spac;

    @FXML
    private TableView<Academie> table;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherListeAcademies();
    }
    

    
   @FXML
private void afficherListeAcademies() {
    AcademieService as = new AcademieService();
    try {
        // Récupérer la liste des académies depuis le service
        ObservableList<Academie> academies = FXCollections.observableArrayList(as.afficherListeA());
ida.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));
      
nomac.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));
       adc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAdresse()));
       numac.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNumtel()));
       spac.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSportpropose()));
        // Afficher les données dans le TableView
        table.setItems(academies);
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Afficher une alerte en cas d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'affichage de la liste des académies.");
        alert.show();
    }
}
public void supprimer(ActionEvent e){
        AcademieService as = new AcademieService();
        as.supprimeracademie(table.getSelectionModel().getSelectedItem().getId());
        afficherListeAcademies();
    }
public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutacademieFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            AjoutacademieFXMLController a = loader.getController();
          

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifieracademieFXML.fxml"));
         
          
            newLoadedPane = loader.load();
            ModifieracademieFXMLController m = loader.getController();
           /* m.pass(table.getSelectionModel().getSelectedItem());*/
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchor.getChildren().clear();
        anchor.getChildren().add(newLoadedPane);
       

    }
    

     
}
