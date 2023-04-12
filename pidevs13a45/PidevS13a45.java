/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevs13a45;

import entites.Participation;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.EvenementService;
import services.ExerciceService;
import services.ParticipationService;
import utils.MyDB;

/**
 *
 * @author Ennou
 */
public class PidevS13a45 {
 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            // TODO code application logic here
            MyDB db = MyDB.getInstance();
            // MyDB db1 = MyDB.getInstance();
            System.out.println(db);
            //  System.out.println(db1);
            EvenementService ev = new EvenementService();
            ExerciceService exercice = new ExerciceService();
            ParticipationService par = new ParticipationService();
            
            
            
            
            
            
            
            /////////////////////////////////////////////////////////////////////////EVENEMENT/////////////////////////
            // Définir le format de la date
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
            // Convertir la chaîne de caractères en objet java.util.Date
            Date date = sdf.parse("23-01-2024");
            
            // Convertir l'objet java.util.Date en objet java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            
            
            String imageUrl = "C:\\Users\\User\\Desktop\\sport2.jpg";
            imageUrl =  imageUrl.replace("\\", "/");
            
            
            ////// Créer l'objet Evenement avec la date
           // Evenement e = new Evenement("eee", "Tennis", "hlif", "lllllllllll",imageUrl, sqlDate);
           /* try {
                ev.ajoutEvenement(e);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            */

           
           ////////////////suppression Evenement
         /*   try {
                ev.supprimerEvenement(11);
            } catch (SQLException ex) {
                Logger.getLogger(Pidev.class.getName()).log(Level.SEVERE, null, ex);
            }
           */ 
         
         
         //////////////////modification Evenement
     /*   Evenement e1 = new Evenement(12,"eee", "Tennis", "hlif", "lllllllllll",imageUrl, sqlDate);
            try {
                // Appeler la fonction modifierEvenement() en passant l'objet Evenement en paramètre
                ev.modifierEvenement(e1);
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la modification de l'événement : " + ex.getMessage());
            }
            
            
            
         ////////////////////Affichage liste evenement
            try{
                System.out.println(ev.afficherListeEvenement());
                
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }*/
            
            
     
     
     
     
     
     
     ////////////////////////////////////////////////////////////////////EXERCICE///////////////////////////////
     
         ////////////////////Affichage liste exercice
            try {
                System.out.println(exercice.afficherListeExercice());
            } catch (SQLException ex) {
                Logger.getLogger(PidevS13a45.class.getName()).log(Level.SEVERE, null, ex);
            }
            
 
            
            
            ///////////////////Ajout exercice
            String imageUrlEx = "C:\\Users\\User\\Desktop\\ProjetPidevErij\\ProjetPidev - Avec table user et particpation\\public\\Back\\img";
            imageUrlEx =  imageUrlEx.replace("\\", "/");
           /* 
             Exercice exe = new Exercice(4,"exerciceerij", "Desc exercice", "2h",imageUrlEx);
            try {
                exercice.ajoutExercice(exe);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            

             ////////////////suppression Exercice
            try {
                exercice.supprimerExercice(10);
            } catch (SQLException ex) {
                Logger.getLogger(Pidev.class.getName()).log(Level.SEVERE, null, ex);
            }
           */
            
             //////////////////modification Exercuce
          /*Exercice exe1 = new Exercice(11,2,"exercice erij", "Description exercice", "3h",imageUrlEx);
            try {
                // Appeler la fonction modifierEvenement() en passant l'objet Evenement en paramètre
                exercice.modifierExercice(exe1);
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la modification de l'exercice : " + ex.getMessage());
            }*/
          
          
          
          
          
          
          
          
          
          //////////////////////////////////////////////////PARTICIPATION////////////////////////////
          
          ////////////////////Affichage liste participation
            try {
                System.out.println(par.afficherListeParticipation());
            } catch (SQLException ex) {
                Logger.getLogger(PidevS13a45.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          
           ///////////////////Ajout particpation
            
           
             Participation p = new Participation(4,3);
            try {
                par.ajoutParticipation(p);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
          
          
          
            ////////////////suppression Participation
            try {
                par.supprimerParticipation(16);
            } catch (SQLException ex) {
                Logger.getLogger(PidevS13a45.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        }
        catch (ParseException ex) {
            Logger.getLogger(PidevS13a45.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
        
        
        
        
       }

}
