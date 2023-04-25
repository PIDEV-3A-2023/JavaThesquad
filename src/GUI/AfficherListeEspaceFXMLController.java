/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategorieLocation;
import Entities.Espace;
import Services.CategorieLocationService;
import Services.EspaceService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class AfficherListeEspaceFXMLController implements Initializable {
      @FXML
    private AnchorPane anchores;

    @FXML
    private TableView<Espace> tablespace;

    @FXML
    private TableColumn<Espace, Integer> idspace;

    @FXML
    private TableColumn<Espace, String> nomesp;

    @FXML
    private TableColumn<Espace, String> caract;

    @FXML
    private TableColumn<Espace, String> img;

    @FXML
    private TableColumn<Espace, String> adresse;

    @FXML
    private TableColumn<Espace, String> dispo;

    @FXML
    private TableColumn<Espace, Date> tarif;

    @FXML
    private TableColumn<Espace, Double> prixloc;

    @FXML
    private TableColumn<Espace, String> cat;

    @FXML
    private Label labeles;

    @FXML
    private Button ajouteres;

    @FXML
    private Button modifieres;

    @FXML
    private Button suppespace;
    
       @FXML
    private TextField txtfieldrecherche;

    @FXML
    private Label labelrecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherListeES();
    }    

    private void afficherListeES() {
        EspaceService es = new EspaceService();
        
         try {
        // Récupérer la liste des espaces depuis le service
        ObservableList<Espace> espaces = FXCollections.observableArrayList(es.afficherListeES());
        
        
        
        
idspace.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));
      
nomesp.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));
       caract.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCaracteristique()));
       img.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getImage()));
       adresse.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAdresse()));
        dispo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDispo()));
        
        tarif.setCellValueFactory(cellData -> {
    Date dateValue = cellData.getValue().getTarifhoraire();
    return new SimpleObjectProperty<>(dateValue);
        });
        /* prixloc.setCellValueFactory(cellData -> {
        Double prixLocation = cellData.getValue().getPrixlocation();
        return new SimpleDoubleProperty(prixLocation);
        });*/
         prixloc.setCellValueFactory(cellData ->
                    new SimpleObjectProperty<Double>(Double.valueOf(cellData.getValue().getPrixlocation())));
        cat.setCellValueFactory(cellData ->
                new SimpleObjectProperty(cellData.getValue().getCategorieloc().getNom()));


       
      
        // Afficher les données dans le TableView
        tablespace.setItems(espaces);
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Afficher une alerte en cas d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'affichage de la liste des espaces.");
        alert.show();
    }
        
    }
       public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
      
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjouterEspaceFXML.fxml"));
            newLoadedPane = loader.load();
            
            
            AjouterEspaceFXMLController a = loader.getController();
           
            
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchores.getChildren().clear();
        anchores.getChildren().add(newLoadedPane);
        
        
        

    }
        public void redirigermod(ActionEvent e){
        Pane newLoadedPane = null;
      
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifierEspaceFXML.fxml"));
            newLoadedPane = loader.load();
            
            
            ModifierEspaceFXMLController m = loader.getController();
            m.setEspace(tablespace.getSelectionModel().getSelectedItem());
            
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchores.getChildren().clear();
        anchores.getChildren().add(newLoadedPane);
        
        
        

    }
        public void supprimerespace(ActionEvent e){
        EspaceService esser = new EspaceService();
        esser.supprimerEspace(tablespace.getSelectionModel().getSelectedItem().getId());
        afficherListeES();
    }
    
}
