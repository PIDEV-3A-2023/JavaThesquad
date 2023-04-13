/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Academie;
import entities.Salle;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.MyDB;

/**
 *
 * 
 * 
 * 
 * 
 * 
 *
 * @author LENOVO
 */
public class SalleService implements ISalle<Salle> {
public Connection conx;
public Statement stm;
AcademieService aa=new AcademieService();

public SalleService(){
conx=MyDB.getInstance().getConx();}

   @Override
public void ajoutSalle(Salle s) throws SQLException {
    String req = "INSERT INTO `Salle`(`nom`, `capacite`, `equipement`,`academie_id`) VALUES (?,?,?,?)";
    PreparedStatement ps = conx.prepareStatement(req);

    // Vérifier si les champs ne sont pas vides
    String nom = s.getNom();
    int capacite = s.getCapacite();
    int equipement = s.getEquipement();
    Academie academie = s.getAcademie();
    
    if (!nom.isEmpty() && capacite > 0 && equipement > 0 && academie != null) {
        // Vérifier si la capacité est entre 10 et 50
        if (capacite >= 10 && capacite <= 50) {
            // Vérifier si l'équipement est inférieur ou égal à 10
            if (equipement <= 10) {
                //1 = index de colonne
                ps.setString(1, nom);
                ps.setInt(2, capacite);
                ps.setInt(3, equipement);
                ps.setInt(4, academie.getId());
                ps.executeUpdate();
                System.out.println("Salle ajoutée");
            } else {
                // Afficher un message d'erreur si l'équipement est supérieur à 10
                System.out.println("Erreur : L'équipement doit être inférieur ou égal à 10.");
            }
        } else {
            // Afficher un message d'erreur si la capacité n'est pas entre 10 et 50
            System.out.println("Erreur : La capacité doit être entre 10 et 50.");
        }
    } else {
        // Afficher un message d'erreur si un champ est vide
        System.out.println("Erreur : Les champs ne doivent pas étre nuls ");
    }
}


    

    @Override
    public List<Salle> afficherListesalles() throws SQLException {
        String req="SELECT * FROM `Salle`";
      stm=conx.createStatement();
      ResultSet rs=stm.executeQuery(req);
      List<Salle> salles=new ArrayList<>();
      while(rs.next()){
        Salle s=new Salle(rs.getInt("id"), rs.getString("nom"), rs.getInt("capacite"), rs.getInt("equipement"), aa.getAcademiee(rs.getInt("academie_id")));
        salles.add(s);
              
      }
      return salles;
    }

    @Override
    public Salle getSalle(int id) throws SQLException {
          Salle Salle =null;
        try {
            String req="SELECT * FROM `Salle` " +
                    "where id=?";
            PreparedStatement ps=conx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                Salle ss = new Salle(rs.getInt("id"), rs.getString("nom"), rs.getInt("capacite"), rs.getInt("equipement"), aa.getAcademiee(rs.getInt("academie_id")));
                Salle=ss;

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

        return Salle;
    }
    
    @Override
    public boolean modifiersalle(Salle object) throws SQLException {
       String req = "UPDATE `Salle` " +        
             "SET academie_id=?, nom=?, capacite=?, equipement=? " +
             "WHERE id=?";
        try {
            PreparedStatement stm = conx.prepareStatement(req);
          
            stm.setInt(1, object.getAcademie().getId());
            stm.setString(2, object.getNom());
            stm.setInt(3, object.getCapacite());
            stm.setInt(4, object.getEquipement());
            stm.setInt(5,object.getId());
            
            stm.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }

    @Override
    public boolean supprimersalle(int id) {
       String req="DELETE FROM `Salle` " +
                "WHERE id=?";
    try{
    PreparedStatement stm=conx.prepareStatement(req);
    stm.setInt(1,id);
    stm.executeUpdate();
    }catch (SQLException exception){
        System.out.println(exception.getMessage());
    }

        return true;
    }
    }
    

