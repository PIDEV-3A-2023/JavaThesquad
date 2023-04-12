/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entites.Evenement;
import entites.Exercice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ExerciceService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AffichageExFXMLController implements Initializable {

    @FXML
    private TableView<Exercice> listeExercice;
    private TableColumn<Exercice, Integer> idCol;
    private TableColumn<Exercice, Integer> idEventCol;
    private TableColumn<Exercice, String> nomEventCol;
    private TableColumn<Exercice, String> nomCol;
    private TableColumn<Exercice, String> dureeCol;
    private TableColumn<Exercice, String> descriptionCol;
    private TableColumn<Exercice, Void> supprimerCol;
    private TableColumn<Exercice, Void> modifierCol;
    @FXML
    private Button AjoutB;
    @FXML
    private Button miseajour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol=new TableColumn<>("Id");
        idEventCol = new TableColumn<>("Evenement-ID");
        nomEventCol = new TableColumn<>("Evenement-Nom");
        nomCol = new TableColumn<>("Nom");
        dureeCol = new TableColumn<>("Duree");
        descriptionCol = new TableColumn<>("Description");
        supprimerCol = new TableColumn<>("");
        modifierCol = new TableColumn<>("");

        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idEventCol.setCellValueFactory(new PropertyValueFactory<>("evenementId"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
       nomEventCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvenementNom())); // Modification de cette ligne
    
         
         
        supprimerCol.setCellFactory(new Callback<TableColumn<Exercice, Void>, TableCell<Exercice, Void>>() {
            @Override
            public TableCell<Exercice, Void> call(TableColumn<Exercice, Void> param) {
                final TableCell<Exercice, Void> cell = new TableCell<Exercice, Void>() {
                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Exercice exercice = getTableView().getItems().get(getIndex());
                            ExerciceService exService = new ExerciceService();
                            try {
                                exService.supprimerExercice(exercice.getId());
                                // Afficher une fenêtre de confirmation
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de suppression");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez vous confirmer la suppression?");
                                alert.showAndWait();
                            } catch (SQLException ex) {
                                Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            listeExercice.getItems().remove(exercice);
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
        
        
    modifierCol.setCellFactory(new Callback<TableColumn<Exercice, Void>, TableCell<Exercice, Void>>() {
        @Override
        public TableCell<Exercice, Void> call(TableColumn<Exercice, Void> param) {
            final TableCell<Exercice, Void> cell = new TableCell<Exercice, Void>() {
                private final Button btn = new Button("Modifier");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Exercice exercice = getTableView().getItems().get(getIndex());
                       
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierExerciceFXML.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                            ModifierExerciceFXMLController controller = loader.getController();
                            controller.setExercice(exercice);
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
        
        
   listeExercice.getColumns().addAll(idCol,idEventCol,nomEventCol,nomCol, dureeCol, descriptionCol, supprimerCol,modifierCol);
   ExerciceService exService = new ExerciceService();
        List<Exercice> exercice=new ArrayList<>();
        try {
            exercice = exService.afficherListeExercice();
           

        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeExercice.getItems().addAll(exercice);

    }    

    @FXML
    private void ajouterExercice(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutExerciceFXML.fxml"));
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

    @FXML
    private void miseajourEx(ActionEvent event) {
        listeExercice.getItems().clear(); // Efface les anciens éléments de la liste
        ExerciceService exService = new ExerciceService();
        List<Exercice> exercices = new ArrayList<>();
        try {
            exercices = exService.afficherListeExercice();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeExercice.getItems().addAll(exercices); // Ajoute les nouveaux éléments de la liste
    }
    
}
