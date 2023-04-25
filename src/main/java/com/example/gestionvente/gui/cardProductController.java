package com.example.gestionvente.gui;

import com.example.gestionvente.entites.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class cardProductController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prod_addBtn;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private Spinner<?> prod_spinner;

    @FXML
    void addBtn(ActionEvent event) {

    }
    private Produit prodData;
    private Image image;

    public void setData(Produit prodData){
        this.prodData=prodData;
        prod_name.setText(prodData.getNom());
        prod_price.setText(String.valueOf(prodData.getPrix()));
        String path ="File: "+ prodData.getImage() ;
        System.out.println(path);
        image=new Image(path,190,94,false,true);
        prod_imageView.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}