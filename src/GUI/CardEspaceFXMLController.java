/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Espace;
import Utils.DB;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CardEspaceFXMLController implements Initializable {
     @FXML
    private Label adresse;

    @FXML
    private Label caracteristic;

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button details;

    @FXML
    private ImageView espaceImage;

    @FXML
    private Label espace_prix;

    @FXML
    private Label espacename;

    
    
    private Espace espData;
    public Connection conx;
    private Image image;
    private String esp_image;
    
    private Alert alert;
   

    public CardEspaceFXMLController(){
        conx= DB.getInstance().getConx();

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Espace espData) {
    this.espData = espData;
    esp_image = espData.getImage();
    espacename.setText(espData.getNom());
    adresse.setText(espData.getAdresse());
    espace_prix.setText(String.valueOf(espData.getPrixlocation()));
    String path = "File:" + espData.getImage();
    image = new Image(path, 190, 94, false, true);
    espaceImage.setImage(image);
    Double pr = espData.getPrixlocation();

}
    
}
