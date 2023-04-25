package com.example.gestionvente.healtConnect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HealthConnectFX extends Application {


    Parent root;
    Stage stage;



    @Override
    public void start(Stage primaryStage)  {

        this.stage = primaryStage;
        try{
        //root= FXMLLoader.load(getClass().getResource("/gui/AfficheListeCategorie.fxml"));
        root= FXMLLoader.load(getClass().getResource("/gui/ProduitFront.fxml"));
            //root= FXMLLoader.load(getClass().getResource("/gui/AfficheListeProduit.fxml"));
            //System.out.println(getClass().getResource("/gui/AfficheListeCategorie.fxml"));
            //root= FXMLLoader.load(getClass().getResource("/gui/AfficheListeCategorie.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("GestionVente");
            stage.show();



        }catch (IOException exception){
            System.out.println(exception.getMessage());
            return;
        }





    }


    public static void main(String[] args) {
        launch(args);
    }
}
