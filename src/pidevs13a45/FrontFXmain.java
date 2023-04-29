/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package pidevs13a45;

import java.io.IOException;
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
 * @author User
 */
public class FrontFXmain extends Application {
    Parent root;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
       
        this.stage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/AffichageEvenementFront.fxml"));
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
