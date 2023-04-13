/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Salle;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ISalle<T> {
      public void ajoutSalle(T s)throws SQLException;
    public List<Salle> afficherListesalles()throws SQLException;
     public Salle getSalle(int id)throws SQLException;
     public boolean modifiersalle(Salle object) throws SQLException;
     public boolean supprimersalle(int id);
}
