/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entites.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AffichageEvFXMLController implements Initializable {
    @FXML
    private AnchorPane intAffichage;
    @FXML
    private TableView<Evenement> listeE;
    private TableColumn<Evenement, Integer> idCol;
    private TableColumn<Evenement, String> nomCol;
    private TableColumn<Evenement, String> typeCol;
    private TableColumn<Evenement, String> adresseCol;
    private TableColumn<Evenement, String> descriptionCol;
    private TableColumn<Evenement, String> imageCol;
    private TableColumn<Evenement, Date> dateCol;
    private TableColumn<Evenement, Void> supprimerCol;
    private TableColumn<Evenement, Void> modifierCol;
    private Button AjoutEv;
    @FXML
    private Button miseajour;
    @FXML
    private Button AjoutB;
    @FXML
    private Button AjoutB2;
   
    /**
     * Initializes the controller class.
     */
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol=new TableColumn<>("Id");
        nomCol = new TableColumn<>("Nom");
        typeCol = new TableColumn<>("Type");
        adresseCol = new TableColumn<>("Adresse");
        descriptionCol = new TableColumn<>("Description");
        dateCol = new TableColumn<>("Date");
        supprimerCol = new TableColumn<>("");
        modifierCol = new TableColumn<>("");

        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        //////////Ajout du bouton supprimer//////////////
        supprimerCol.setCellFactory(new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement evenement = getTableView().getItems().get(getIndex());
                            EvenementService evService = new EvenementService();
                            try {
                                evService.supprimerEvenement(evenement.getId());
                                // Afficher une fenêtre de confirmation
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de suppression");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez vous confirmer la suppression?");
                                alert.showAndWait();
                            } catch (SQLException ex) {
                                Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            listeE.getItems().remove(evenement);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        
        
        
        modifierCol.setCellFactory(new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
        @Override
        public TableCell<Evenement, Void> call(TableColumn<Evenement, Void> param) {
            final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
                private final Button btn = new Button("Modifier");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Evenement evenement = getTableView().getItems().get(getIndex());
                       
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEventFXML.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                            ModifierEventFXMLController controller = loader.getController();
                            controller.setEvenement(evenement);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    });
        
        
        
        
        listeE.getColumns().addAll(idCol,nomCol, typeCol, adresseCol,descriptionCol, dateCol, supprimerCol,modifierCol);
        
        
        EvenementService evService = new EvenementService();
        List<Evenement> events=new ArrayList<>();
        try {
            events = evService.afficherListeEvenement();
           

        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeE.getItems().addAll(events);
        
        

    }    

    
    
       @FXML
    private void ajouterEvenement(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementFXML.fxml"));
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
    
    
    ////////////////////////////code du bouton refresh
    @FXML
    private void miseajour(ActionEvent event) {
         listeE.getItems().clear(); // Efface les anciens éléments de la liste
        EvenementService evService = new EvenementService();
        List<Evenement> events = new ArrayList<>();
        try {
            events = evService.afficherListeEvenement();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeE.getItems().addAll(events); // Ajoute les nouveaux éléments de la liste
    }

    @FXML
    private void ajouterExercice(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageExFXML.fxml"));
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
