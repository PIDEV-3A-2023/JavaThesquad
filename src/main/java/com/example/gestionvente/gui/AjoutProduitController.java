package com.example.gestionvente.gui;

import com.example.gestionvente.entites.CategorieProduit;
import com.example.gestionvente.entites.Produit;
import com.example.gestionvente.services.CategorieProduitService;
import com.example.gestionvente.services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjoutProduitController implements Initializable {

    @FXML
    private ComboBox<CategorieProduit> category;

    @FXML
    private TextField descProduit;


    @FXML
    private TextField image;

    @FXML
    private TextField nomProduit;

    @FXML
    private Pane pane;

    CategorieProduitService cps=new CategorieProduitService();

    @FXML
    private TextField prix;
    ProduitService produitService=new ProduitService();

    @FXML
    void ajouter(ActionEvent event) {
        //Recupere les valeurs des champs
        String nom=nomProduit.getText();
        String desc=descProduit.getText();
        String img=image.getText();
        CategorieProduit nomCategorie=category.getValue();
        Float prixProduit=Float.parseFloat(prix.getText());

        if (nom.isEmpty() || desc.isEmpty() || img.isEmpty() || prixProduit.isNaN()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        if (nom == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir nom de Produit  !");
            alert.showAndWait();
            return;
        }

        if (desc.length() < 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La description doit contenir au moins 20 caractères !");
            alert.showAndWait();
            return;
        }

        Produit produit = new Produit(nomProduit.getText(),descProduit.getText(),image.getText(),Float.parseFloat(prix.getText()),category.getValue());
        System.out.println(produit);
        produitService.ajouter(produit);

        // Affichage d'un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Exercice ajouté avec succès !");
        alert.showAndWait();


        // Effacement des champs après ajout
        nomProduit.setText("");
        descProduit.setText("");
        image.setText("");
        prix.setText("");
        category.setValue(null); // Effacement de la sélection dans le ComboBox


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            category.getItems().addAll(cps.afficher());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
