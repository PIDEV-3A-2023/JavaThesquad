/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategorieLocation;
import Services.CategorieLocationService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class ModifierCategorieLocationFXMLController implements Initializable {
 ;
@FXML
    private AnchorPane anchorm;

    @FXML
    private Label labelmod;

    @FXML
    private Label labeldescc;

    @FXML
    private TextField txtfieldNommod;

    @FXML
    private TextArea textareaDescmod;

    @FXML
    private Button btnmod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    


    CategorieLocationService cps=new CategorieLocationService();
    CategorieLocation categorieloc=new CategorieLocation();

    @FXML
    public void modifier (ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        //String regex2 = "^[1-9][0-9]*$";


        Pattern pattern = Pattern.compile(regex);

        if ( pattern.matcher(txtfieldNommod.getText()).matches() && pattern.matcher(textareaDescmod.getText()).matches() ) {
            categorieloc.setNom(txtfieldNommod.getText());
            categorieloc.setDescription(textareaDescmod.getText());

                cps.modifiercategorieL(categorieloc);
            }


        }




 public void pass(CategorieLocation categorieloc){
        this.categorieloc=categorieloc;
        txtfieldNommod.setText(categorieloc.getNom());
        textareaDescmod.setText((categorieloc.getDescription()));

    }


  

}

    
    

