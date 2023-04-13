/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ICours<T> {
      public void ajoutCours(T c)throws SQLException;
    public List<Cours> afficherListecours()throws SQLException;
     public Cours getCours(int id)throws SQLException;
     public boolean modifiercours(Cours object) throws SQLException;
     public boolean supprimercours(int id);
}
