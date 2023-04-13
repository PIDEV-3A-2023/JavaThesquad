/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnect;
import Entities.CategorieLocation;
import Entities.Espace;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DB;
import Services.CategorieLocationService;
import Services.EspaceService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author lobna
 */
public class HealthConnect {
    DB db = DB.getInstance();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        
       
        
        CategorieLocation c4 = new CategorieLocation("terrain","terr");
        CategorieLocationService catservice= new CategorieLocationService();
        try {
          catservice.ajoutCategorielocation(c4);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
         //CategorieLocationService catservice= new CategorieLocationService();
        //catservice.supprimercategorieL(39);
       
       

        /*try {
            catservice.ajoutCategorielocation(c1);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }*/
        
        // afficher toutes les categories
        System.out.println("/****Affichage d'une liste des catégories ****/");
         try {
            System.out.println(catservice.afficherListeC());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          //afficher une catégorie selon son id 
            System.out.println("/****Affichage d'une catégorie selon id ****/");
            System.out.println(catservice.get(40));
            
       
        // pour modifier une catégorie de location
         // CategorieLocation categorielocation=new CategorieLocation();
         // categorielocation.setId(19);
         // categorielocation.setNom("terrain handball");
         // categorielocation.setDescription("terrain handball");
         // catservice.modifiercategorieL(categorielocation);
          
          //pour supprimer une catégorie de location
         // catservice.supprimercategorieL(52);
            
        //Espace es2 = new Espace("terrain volley rades","terrain volley dispo","jardinjapo-63f73150c0003.jpg","rades","disponible", date, 456.5,40);
       
             // ajout d'une date avec l'heure et faire la conversion de date en string
       
        //on appelle l'id catégorie espace 
        CategorieLocation cat3 = catservice.get(40);
        LocalDate localDate = LocalDate.of(2023, 4, 4);

  CategorieLocationService sserv=new CategorieLocationService();
  
      EspaceService spa=new EspaceService(); 
  // supprimer un espace
     spa.supprimerEspace(41);
     spa.supprimerEspace(42);
     spa.supprimerEspace(43);
     spa.supprimerEspace(44); 
     

     //*******************************************
     // les mois sont indexés à partir de 0 donc 0 c'est le mois de janvier
     Date date = new Date(2023 - 1900, 4, 9);
          //modifier les champs de l'espace   
       Espace space2=new Espace();
          space2.setId(37);
          space2.setNom("terrain volley tayeb mhiri");
          space2.setCaracteristique("terrain volley dispo à taieb mhiri");
          space2.setImage("basket-64035a14d0acb.jpg");
          space2.setAdresse("taieb mhiri sfax");
          space2.setDispo("non disponible");
          space2.setTarifhoraire(date);
          space2.setPrixlocation(345.4);
          space2.setCategorieloc(new CategorieLocation(40,"terrain volleyballrades","terrain volley rades pour pratiquer le volleyball"));
          spa.modifierEspace(space2); /*
          ************************************************************************
          
          
          */
         
        //on ajoute un objet espace
        Espace espace = new Espace( 38, 
    "terrain volley rades", 
    "terrain volley dispo", 
    "jardinjapo-63f73150c0003.jpg", 
    "rade-b*", 
    "disponible",
    date, 
    345.4,
    cat3);
        EspaceService spaceservice= new EspaceService();
        try {
            spaceservice.ajoutEspace(espace);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
    //EspaceService spaceservice= new EspaceService();
           System.out.println("****Affichage des espaces****");
               try {
            System.out.println(spaceservice.afficherListeES());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
        
       
        
    }

    
}
