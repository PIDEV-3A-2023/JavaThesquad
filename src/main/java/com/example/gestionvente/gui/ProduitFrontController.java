package com.example.gestionvente.gui;

import com.example.gestionvente.entites.Produit;
import com.example.gestionvente.services.CategorieProduitService;
import com.example.gestionvente.utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProduitFrontController implements Initializable {
    @FXML
    private TableColumn<?, ?> menuColProduitName;

    @FXML
    private TableColumn<?, ?> menuColProduitPrix;

    @FXML
    private TableColumn<?, ?> menuColProduitQuantite;

    @FXML
    private Pane menuForm;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private TableView<?> menuTableView;

    @FXML
    private Label menuTot;

    private ObservableList<Produit> cardListData= FXCollections.observableArrayList();
    public Connection conx;
    public Statement stm;
    CategorieProduitService categorieProduitService =new CategorieProduitService();
    public ProduitFrontController(){
        conx= MyDB.getInstance().getConx();
    }

    public ObservableList<Produit>menuGetData(){
        ObservableList<Produit>listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM produit";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next()){
                float prix = Float.parseFloat(rs.getString("prix"));
                Produit p = new Produit(rs.getInt("id"),rs.getString("nom"),
                        rs.getString("description"),rs.getString("image")
                        ,prix,categorieProduitService.get(rs.getInt("category_id")));

                listData.add(p);

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
                loader.setLocation(getClass().getResource("/gui/CardProduit.fxml"));
                AnchorPane pane=loader.load();
                cardProductController cardC=loader.getController();
                cardC.setData(cardListData.get(q));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuDisplayCard();
    }
}
