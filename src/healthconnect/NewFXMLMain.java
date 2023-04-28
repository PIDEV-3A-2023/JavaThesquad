/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnect;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

/**
 *
 * @author lobna
 */
public class NewFXMLMain extends Application {
Parent root;
Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
       this.stage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/FrontEspaceFXML.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("catégorie de l'espace ");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
