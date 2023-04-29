/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Academie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CardacademieControllerFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private Button abonne;

    @FXML
    private Label ac_name;

    @FXML
    private Button addBtn;

    @FXML
    private AnchorPane card_form;
     @FXML
    private AnchorPane contenu;

    @FXML
    private HBox snip;

    @FXML
    private HBox snip2;
    @FXML
    private Label adresseLabel;

    @FXML
    private Label numtelLabel;

    @FXML
    private Label sportproposeLabel;
    private Academie acadData;
     public Connection conx;


    private Alert alert;


  public CardacademieControllerFXMLController(){
       conx= MyDB.getInstance().getConx();
  }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Academie acadData) {
    this.acadData = acadData;
  
   ac_name.setText(acadData.getNom());
        adresseLabel.setText(acadData.getAdresse());
        numtelLabel.setText(acadData.getNumtel());
        sportproposeLabel.setText(acadData.getSportpropose());


}
       public void addBtn(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FrontsalleFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            FrontsalleFXMLController ss = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
       if (newLoadedPane != null) {
    card_form.getChildren().clear();
    card_form.getChildren().add(newLoadedPane);
} else {
    System.out.println("newLoadedPane is null");
}

       

    }}



