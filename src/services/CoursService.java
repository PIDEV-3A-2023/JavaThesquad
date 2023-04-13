/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author LENOVO
 */
public class CoursService implements ICours<Cours>{
public Connection conx;
public Statement stm;
SalleService ss=new SalleService();

public CoursService(){
conx=MyDB.getInstance().getConx();}

    @Override
    public void ajoutCours(Cours c) throws SQLException {
          String req="INSERT INTO `Cours`(`nom`, `date`, `duree`, `nbparticipants`, `salle_id`) VALUES (?,?,?,?,?)";
     PreparedStatement ps=conx.prepareStatement(req);
  
     ps.setString(1, c.getNom());
     ps.setDate(2, c.getDate());
     ps.setInt(3, c.getDuree());
     ps.setInt(4, c.getNbparticipants());
     ps.setInt(5, c.getSalle().getId());
     ps.executeUpdate();
     System.out.println("Cours ajoutée");
    }
    

    @Override
    public List<Cours> afficherListecours() throws SQLException {
            String req="SELECT * FROM `Cours`";
      stm=conx.createStatement();
      ResultSet rs=stm.executeQuery(req);
      List<Cours> cours=new ArrayList<>();
      while(rs.next()){
        Cours c=new Cours(rs.getInt("id"), rs.getString("nom"), rs.getDate("date"), rs.getInt("duree"),rs.getInt("nbparticipants"), ss.getSalle(rs.getInt("salle_id")));
        cours.add(c);
              
      }
      return cours;
    }
    

    @Override
    public Cours getCours(int id) throws SQLException {
         Cours Cours =null;
        try {
            String req="SELECT * FROM `Cours` " +
                    "where id=?";
            PreparedStatement ps=conx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                Cours cc = new Cours(rs.getInt("id"), rs.getString("nom"),rs.getDate("date"), rs.getInt("duree"),rs.getInt("nbparticipants"), ss.getSalle(rs.getInt("salle_id")));
                Cours=cc;

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

        return Cours;
    }

    @Override
    public boolean modifiercours(Cours object) throws SQLException {
         String req = "UPDATE `Cours` " +        
             "SET salle_id=?, nom=?, date=?, duree=?, nbparticipants=? " +
             "WHERE id=?";
        try {
            PreparedStatement stm = conx.prepareStatement(req);
          
            stm.setInt(1, object.getSalle().getId());
            stm.setString(2, object.getNom());
            stm.setDate(3, object.getDate());
            stm.setInt(4, object.getDuree());
            stm.setInt(5, object.getNbparticipants());
            stm.setInt(6,object.getId());
            
            stm.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }

    
    @Override
    public boolean supprimercours(int id) {
            String req="DELETE FROM `Cours` " +
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
