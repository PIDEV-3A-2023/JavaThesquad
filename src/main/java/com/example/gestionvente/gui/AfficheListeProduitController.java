package com.example.gestionvente.gui;

import com.example.gestionvente.entites.CategorieProduit;
import com.example.gestionvente.entites.Produit;
import com.example.gestionvente.services.ProduitService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficheListeProduitController implements Initializable {

    ProduitService produitService = new ProduitService();

    @FXML
    private TextField search;
    @FXML
    private javafx.scene.control.ChoiceBox<String> ChoiceBox;

    @FXML
    private TableColumn<Produit, String> descriptionProduit;

    @FXML
    private Pane detail;

    @FXML
    private TableColumn<Produit, String> Categorie;

    @FXML
    private TableColumn<Produit, Integer> idProduit;

    @FXML
    private TableColumn<Produit, String> image;

    @FXML
    private TableColumn<Produit, String> nomProduit;

    @FXML
    private TableColumn<Produit, Float> prix;

    @FXML
    private TableView<Produit> table;

    @FXML
    void afficher(MouseEvent event) {
        Pane newLoadedPane = null;
        try {



            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherDetailleProduit.fxml"));
            newLoadedPane = loader.load();
            AfficherDetailleProduitController c = loader.getController();
            c.ProduitData(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
            System.out.println("aaaa");
        }

        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);


    }

    @FXML
    void ajouter(ActionEvent event) {



        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterProduit.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }


    @FXML
    void modifier(ActionEvent event) {
        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierProduit.fxml"));
            newLoadedPane = loader.load();
            ModifierProduitConntroller c = loader.getController();
            c.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }



    @FXML
    void refresh(ActionEvent event) {

    }

    public void filteredSearch(){
        List<Produit> list_user = produitService.afficher();
        ObservableList<Produit> list = FXCollections.observableArrayList(list_user);
        FilteredList<Produit> fluser = new FilteredList(list, p -> true);
        search.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (ChoiceBox.getValue()) {
                case "Nom":
                    fluser.setPredicate(p -> p.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Categorie":
                    fluser.setPredicate(p -> p.getCategory().getNom().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
            }
        });
        table.setItems(fluser);
        ChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (newVal != null) {
                search.setText("");
            }
        });

    }
//        List<Produit> produitList=produitService.afficher();
//        ObservableList<Produit>list =FXCollections.observableArrayList(produitList);
//        FilteredList<Produit> filteredList=new FilteredList<>(list,p->true){
//
//        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            ObservableList<Produit> produits =
                    FXCollections.observableArrayList(produitService.afficher());

            idProduit.setCellValueFactory(cellData ->
                    new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));

            nomProduit.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getNom()));

            image.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getImage()));

            descriptionProduit.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getDescription()));

            prix.setCellValueFactory(cellData ->
                    new SimpleObjectProperty<Float>(Float.valueOf(cellData.getValue().getId())));


             Categorie.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategory().getNom()));


            table.setItems(produits);

            //recherche
        ChoiceBox.getItems().addAll("Nom","Categorie");
        ChoiceBox.setValue("Nom");
        filteredSearch();

        }


    }

