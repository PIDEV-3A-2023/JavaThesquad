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
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import services.AcademieService;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    @FXML
    private Button trier;
        @FXML
    private Label labelrech;
          @FXML
    private TextField recherche;
          
   @FXML
    private Button exportxl;
   public Connection conx;
public Statement stm;

   
 @Override
public void initialize(URL url, ResourceBundle rb) {
    afficherListeAcademies();
    trier.setOnAction(e -> trierParNom()); // Appeler la méthode trierParNom() lorsque le bouton est cliqué
    Rechercher();
   
}

    @FXML
private void trierParNom() {
    // Récupérer la liste des académies depuis le TableView
    ObservableList<Academie> academies = table.getItems();
    
    // Trier la liste par nom
    table.getSortOrder().clear(); // Effacer tout tri précédent
    nomac.setSortType(TableColumn.SortType.ASCENDING); // Spécifier le type de tri (ascendant)
    table.getSortOrder().add(nomac); // Ajouter la colonne de tri
    table.sort(); // Appliquer le tri
}

   
   @FXML 
private void Rechercher() {
    ObservableList<Academie> academies = table.getItems();
    FilteredList<Academie> filterData = new FilteredList(academies,a->true);
    recherche.setOnKeyReleased(a->{
        recherche.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate((Predicate<? super Academie >) ac->{
                if(newValue==null){
                    return true;
                }
                String toLowerCaseFilter = newValue.toLowerCase();
                if(ac.getNom().contains(newValue)){
                    return true;
                } else if(ac.getAdresse().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if (ac.getNumtel().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if(ac.getSportpropose().toLowerCase().contains(toLowerCaseFilter)){
                    return true;
              
                }
                return false;
            });
        });
        final SortedList<Academie> acads = new SortedList<>(filterData);
        acads.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(acads);
    });
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

    @FXML
public void supprimer(ActionEvent e){
        AcademieService as = new AcademieService();
        as.supprimeracademie(table.getSelectionModel().getSelectedItem().getId());
        afficherListeAcademies();
    }
    @FXML
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
    @FXML
public void redirecttomodif(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifieracademieFXML.fxml"));
         
          
            newLoadedPane = loader.load();
            ModifieracademieFXMLController m = loader.getController();
            m.pass(table.getSelectionModel().getSelectedItem());
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchor.getChildren().clear();
        anchor.getChildren().add(newLoadedPane);
       

    }
    

     
}
