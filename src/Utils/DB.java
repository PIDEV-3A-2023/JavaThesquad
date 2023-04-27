/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lobna
 */
public class DB {
    String url="jdbc:mysql://localhost:3306/pidev";
    String username="root";
    String pwd="";
    
    public static DB instance;
    private Connection conx;
    
     public DB() {
        try {
            conx = DriverManager.getConnection(url, username, pwd);
             System.out.println("Connexion établie");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
      
    }
     public Connection getConx() {
        return conx;
    }
     public void setConx(Connection conx) {
        this.conx = conx;
    }
     public static DB getInstance() {
        if(instance == null) 
            
        instance = new DB();
        
        return instance;
        
    }
     
     
         public static void setInstance(DB instance) {
        DB.instance = instance;
    }
     
     
     
     
    
    
}
