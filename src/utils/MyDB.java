/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class MyDB {
    String url="jdbc:mysql://localhost:3306/pidev";
    String username="root";
    String pwd="";
    
    public static MyDB instance;
    private Connection conx;
    
    //creer une instance de cette classe
    private MyDB(){
        try {
            conx=DriverManager.getConnection(url,username,pwd);
            System.out.println("Connection établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//creer un point d'acces 
    public static MyDB getInstance() {
        if(instance ==null)
            instance = new MyDB();
        return instance;
    }

    public Connection getConx() {
        return conx;
    }
}
