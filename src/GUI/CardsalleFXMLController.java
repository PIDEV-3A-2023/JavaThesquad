/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Academie;
import entities.Salle;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CardsalleFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button addBtn;

    @FXML
    private Label capaciteLabel;

    @FXML
    private AnchorPane card_form;

    @FXML
    private AnchorPane contenu;

    @FXML
    private Label equipementLabel;

    @FXML
    private Label nombrepartabel;

    @FXML
    private Label nomsalle;

    @FXML
    private HBox snip;

    @FXML
    private HBox snip2;
    private Salle salledata;
     public Connection conx;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void setData(Salle salledata) {
    this.salledata = salledata;
  
    nomsalle.setText(salledata.getNom());
    capaciteLabel.setText(Integer.toString(salledata.getCapacite()));
    equipementLabel.setText(Integer.toString(salledata.getEquipement()));
 
}

        
        

 public void addbtn(int academieId) {
    try {
        // Récupérer la connexion à la base de données
        Connection conx = MyDB.getInstance().getConx();
        
        // Récupérer toutes les salles de l'académie depuis la base de données
        String query = "SELECT * FROM Salle WHERE academie_id = ?";
        PreparedStatement statement = conx.prepareStatement(query);
        statement.setInt(1, academieId);
        ResultSet rs = statement.executeQuery();

        // Parcourir les résultats et afficher les informations de chaque salle
        while (rs.next()) {
            int salleId = rs.getInt("id");
            String nom = rs.getString("nom");
            int capacite = rs.getInt("capacite");
            int equipement = rs.getInt("equipement");
            System.out.println("Salle : " + nom);
            System.out.println("Capacité : " + capacite);
            System.out.println("Équipement : " + equipement);
            System.out.println();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    
}
