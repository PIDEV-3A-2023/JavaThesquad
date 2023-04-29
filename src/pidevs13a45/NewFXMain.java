/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevs13a45;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ennou
 */
public class NewFXMain extends Application {
    
    Parent root;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
      
        this.stage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/AffichageEvFXML.fxml"));
        } catch (IOException ex) {
              System.out.println(ex.getMessage());
              ex.printStackTrace();
        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Evenement");
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
