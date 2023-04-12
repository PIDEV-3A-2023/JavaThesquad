/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ennou
 */

    
   public class MyDB {
     String url="jdbc:mysql://localhost:3306/pidev";
    String username="root";
    String pwd="";
    
    public static MyDB instance;
    private Connection conx;
    
    private MyDB(){
        try{
        conx=DriverManager.getConnection(url,username,pwd);
         System.out.println("Connexion établie");

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getConx() {
        return conx;
    }

    public void setConx(Connection conx) {
        this.conx = conx;
    }
    
    
    
}

    
    
    
    

