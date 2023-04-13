package com.example.gestionvente.gui;

import com.example.gestionvente.entites.CategorieProduit;
import com.example.gestionvente.entites.Produit;
import com.example.gestionvente.services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AjoutProduitController {

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

    @FXML
    private TextField prix;
    ProduitService produitService=new ProduitService();

    @FXML
    void ajouter(ActionEvent event) {
        String regex = "^[a-zA-Z]*$";
        //String regex2 = "^[1-9][0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(nomProduit.getText()).matches() && pattern.matcher(descProduit.getText()).matches()
        && pattern.matcher(image.getText()).matches() && pattern.matcher(prix.getText()).matches() && pattern.matcher(category.getValue().getNom()).matches()) {
            Produit produit = new Produit(nomProduit.getText(),descProduit.getText(),image.getText(),Float.parseFloat(prix.getText()),category.getValue());
            produitService.ajouter(produit);

        }
    }
}
