/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Espace;
import Services.CategorieLocationService;
import Services.EspaceService;
import Utils.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontEspaceFXMLController implements Initializable {
    @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuDisplayCard();
        menuGetData();
        
    }    
    
    
      private ObservableList<Espace> cardListData= FXCollections.observableArrayList();
    public Connection conx;
    public Statement stm;
   
    private int getid;
    private Alert alert;

    EspaceService espaceservice =new EspaceService();
    CategorieLocationService cat = new CategorieLocationService();
    public FrontEspaceFXMLController(){
        conx= DB.getInstance().getConx();
    }

    public ObservableList<Espace>menuGetData(){
        ObservableList<Espace>listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM espace";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next()){
                Double prix = Double.parseDouble(rs.getString("prixlocation"));
                Espace es = new Espace(rs.getInt("id"),rs.getString("nom"),
                        rs.getString("adresse"),rs.getString("image")
                        ,prix);

                listData.add(es);

            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return listData;
    }
    public void menuDisplayCard(){
        cardListData.clear();
        cardListData.addAll(menuGetData());
        int row=0;
        int column=0;

        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for(int q=0;q<cardListData.size();q++){
            try {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("/GUI/CardEspaceFXML.fxml"));
                AnchorPane pane=loader.load();
                CardEspaceFXMLController cardE=loader.getController();
                cardE.setData(cardListData.get(q));
                System.out.println(cardListData.get(q));
                if(column==3){
                    column=0;
                    row+=1;
                }
                menuGridPane.add(pane,column++,row);
               // GridPane.setMargin(pane,new Insets(10));
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
    
    
    
            
@FXML
    private void rediriger_categorie(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieLocationFXML.fxml"));
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
