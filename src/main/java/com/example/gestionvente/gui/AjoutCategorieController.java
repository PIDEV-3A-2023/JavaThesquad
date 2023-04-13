package com.example.gestionvente.gui;

import com.example.gestionvente.entites.CategorieProduit;
import com.example.gestionvente.services.CategorieProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AjoutCategorieController {

    @FXML
    private javafx.scene.control.Button Button;

    @FXML
    private TextField Nom;

    @FXML
    private TextArea description;

    @FXML
    private Pane pane;

    CategorieProduitService cps=new CategorieProduitService();

    public void ajouter (ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        //String regex2 = "^[1-9][0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        if ( pattern.matcher(Nom.getText()).matches() && pattern.matcher(description.getText()).matches() ) {
            CategorieProduit categorieProduit=new CategorieProduit(Nom.getText(),description.getText());
            try {
                cps.ajouter(categorieProduit);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
    }

}


