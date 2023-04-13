/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnect;

import entities.Academie;
import entities.Cours;
import entities.Salle;
import java.sql.Date;
import java.sql.SQLException;
import services.AcademieService;
import services.CoursService;
import services.SalleService;
import utils.MyDB;

/**
 *
 * @author LENOVO
 */
public class Healthconnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
              MyDB db=MyDB.getInstance();
               //Ajouter une academie
       /*Academie a=new Academie("Maisondejeunes","menzah6","25797518","tennis");
        AcademieService ac1=new AcademieService();
        try{ ac1.ajoutAcademie(a);
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
                }*/
       
        //Ajouter une academie 2eme méthode
        /*System.out.println(db);
        Academie aa=new Academie("handacademy","tunis","25797521","hockey");
        AcademieService a2=new AcademieService();
        try{ a2.ajoutAcademiee(aa);
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
        }*/
        
        //Afficher liste des academies
      /* AcademieService ac3=new AcademieService();
       try {
       System.out.println(ac3.afficherListeA());
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }*/
       
          //get une academie
       /*AcademieService ac4=new AcademieService();
       try {
       System.out.println(ac4.getAcademiee(4));
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }*/
       
       //Modifier une academie
       /*AcademieService ac5=new AcademieService();
       Academie ac=new Academie();
          ac.setId(10);
          ac.setNom("footp");
          ac.setAdresse("rades");
          ac.setNumtel("71599506");
          ac.setSportpropose("sportt");
          
          ac5.modifieracademie(ac);*/
       
       //supprimer une academie
       /* AcademieService ac6=new AcademieService();
       
       System.out.println(ac6.supprimeracademie(1));*/
       
         //Ajouter une salle
      AcademieService ac1=new AcademieService();
       
       Academie a22 = ac1.getAcademiee(10);
         
       Salle salle1 = new Salle("footy",0,0,a22);
       SalleService salleserv = new SalleService();
       try {
           salleserv.ajoutSalle(salle1);
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
    
   //Modifier une salle
     /*SalleService sserv=new SalleService();
     AcademieService as=new AcademieService(); 
             
       Salle coo=new Salle();
          coo.setId(3);
          coo.setNom("coo");
          coo.setCapacite(10);
          coo.setEquipement(8);
          coo.setAcademie(new Academie(35,"hh","hh","hh","hh"));
          sserv.modifiersalle(coo);*/
        
     
     //Afficher la liste des salles
     /*SalleService ss=new SalleService();
       try {
       System.out.println(ss.afficherListesalles());
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }*/
     
     //Récupérer une salle(get salle)
       /*SalleService sss=new SalleService();
       try {
       System.out.println(sss.getSalle(7));
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }*/
       
        //supprimer une salle
      /* SalleService sss=new SalleService();
       
       System.out.println(sss.supprimersalle(35));*/
     
//Ajout d'un cours
//nchouf date
       /*SalleService sss =new SalleService();
       
       Salle ss = sss.getSalle(7);
         Date date = new Date(2023 - 1900, 3, 9);
       Cours dance = new Cours("dance",date,2,50,ss);
       CoursService coursserv = new CoursService();
       try {
           coursserv.ajoutCours(dance);
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }*/
       
  //Afficher la liste des cours
     /*CoursService cc=new CoursService();
       try {
       System.out.println(cc.afficherListecours());
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }*/
     
      //Récupérer cours
     /* CoursService ccv=new CoursService();
       try {
       System.out.println(ccv.getCours(9));
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }*/
     
     
       //Modifier un cours
     /*CoursService ccs=new CoursService();
     SalleService ss=new SalleService(); 
             
       Cours foot=new Cours();
       Date date = new Date(2023 - 1900, 3, 4);
          foot.setId(9);
          foot.setNom("foot");
          foot.setDate(date);
          foot.setDuree(2);
          foot.setNbparticipants(50);
          foot.setSalle(new Salle(13,"cc02",30,8,new Academie(30,"sport_fitness","Tunis,1015","25737716","foot")));
          ccs.modifiercours(foot);*/
     
      //supprimer un cours
      /*CoursService cs=new CoursService();
       
       System.out.println(cs.supprimercours(19));*/
     
     
    }
       
    
}
