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
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import services.CoursService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CoursFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private AnchorPane anchoor;

    @FXML
    private Button btnajoutc;

    @FXML
    private Button btnmodifierc;

    @FXML
    private Button btnsupp;

    @FXML
    private TableColumn<Cours, Date> datec;

    @FXML
    private TableColumn<Cours, Integer> dureec;

    @FXML
    private TableColumn<Cours, Integer> id;

    @FXML
    private Label labelrechc;

    @FXML
    private Label listec;

    @FXML
    private TableColumn<Cours, Integer> nbparrt;

    @FXML
    private TableColumn<Cours, String> nomc;

    @FXML
    private TextField recherchetextfield;

    @FXML
    private TableColumn<Cours, String> salle;

    @FXML
    private TableView<Cours> tablev;
   
      CoursService cs = new CoursService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    afficherListecours();
    Rechercher();
  
    }    
     @FXML
private void afficherListecours() {
  
    try {
        // Récupérer la liste des cours depuis le service
        
    List<Cours> list = cs.afficherListecours();
    ObservableList<Cours> List = FXCollections.observableArrayList(this.cs.afficherListecours());
     System.out.println(cs.afficherListecours());
   
     id.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));
       nomc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));
    datec.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Date>(cellData.getValue().getDate()));
  dureec.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getDuree())));
  nbparrt.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getNbparticipants())));
   salle.setCellValueFactory(cellData ->
    new SimpleStringProperty(cellData.getValue().getSalle().getNom()));
  
    
        
      /*  ObservableList<Cours> cours = FXCollections.observableArrayList(cs.afficherListecours());
id.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));
      
nomc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));
 datec.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Date>(cellData.getValue().getDate()));
  /* datec.setCellValueFactory(cellData -> {
    java.util.Date dateValue = cellData.getValue().getDate();
    LocalDate localDate = Instant.ofEpochMilli(dateValue.getTime())
            .atZone(ZoneId.systemDefault()).toLocalDate();
    return new SimpleObjectProperty<>(Date.valueOf(localDate));
});

      dureec.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getDuree())));
       nbparrt.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getNbparticipants())));
         salle.setCellValueFactory(cellData ->
    new SimpleObjectProperty(cellData.getValue().getSalle().getNom()));*/
     
        // Afficher les données dans le TableView
        tablev.setItems(List);
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Afficher une alerte en cas d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'affichage de la liste des cours.");
        alert.show();
    }
}

  @FXML
private void Rechercher() {
    ObservableList<Cours> cours = tablev.getItems();
    FilteredList<Cours> filterData = new FilteredList(cours,c->true);
    recherchetextfield.setOnKeyReleased(c->{
        recherchetextfield.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate((Predicate<? super Cours >) cc->{
                if(newValue==null){
                    return true;
                }
                
                String toLowerCaseFilter = newValue.toLowerCase();
                if(cc.getNom().contains(newValue)){
                    return true;
                } else if (Integer.toString(cc.getDuree()).contains(newValue)) {
                    return true;
                } else if(Integer.toString(cc.getNbparticipants()).contains(newValue)){
                    return true;
              
                }
                return false;
            });
        });
        final SortedList<Cours> crs = new SortedList<>(filterData);
        crs.comparatorProperty().bind(tablev.comparatorProperty());
        tablev.setItems(crs);
    });
}

    @FXML
public void supprimer(ActionEvent e){
        CoursService cs= new CoursService();
        cs.supprimercours(tablev.getSelectionModel().getSelectedItem().getId());
        afficherListecours();
    }
    @FXML
public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutcoursFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            AjoutcoursFXMLController a = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchoor.getChildren().clear();
        anchoor.getChildren().add(newLoadedPane);
       

    }
    @FXML
public void redirecttomodif(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifiercoursFXML.fxml"));
         
          
            newLoadedPane = loader.load();
            ModifiercoursFXMLController m = loader.getController();
             m.pass(tablev.getSelectionModel().getSelectedItem());
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchoor.getChildren().clear();
        anchoor.getChildren().add(newLoadedPane);
       

    }
    

     
}


