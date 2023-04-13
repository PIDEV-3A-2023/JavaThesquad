/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Academie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifieracademieFXMLController implements Initializable {
  @FXML
    private Label adresse;

    @FXML
    private TextField adtext;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button modbtn;

    @FXML
    private Label nom;

    @FXML
    private TextField nomtxt;

    @FXML
    private Label numtel;

    @FXML
    private TextField numteltext;

    @FXML
    private Label sportpropose;

    @FXML
    private TextField sptx;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    /*public void pass(Academie academie){
        this.academie=academie;
        txtfieldNommod.setText(categorieloc.getNom());
        textareaDescmod.setText((categorieloc.getDescription()));

    }*/

    }


