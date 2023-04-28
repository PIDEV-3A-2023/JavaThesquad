/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class Rating {
    int id_note_espaces;
    Utilisateur user;
    int id_note;
    int note;
    
    
    
    
   

    public Rating(int id_note_espaces, Utilisateur user, int id_note, int note) {
        this.id_note_espaces = id_note_espaces;
        this.user = user ;
        this.id_note = id_note;
        this.note = note;
    
    }
     public int getUserId() {
        return user.getId();
    }

    public int getId_note_espaces() {
        return id_note_espaces;
    }

    public void setId_note_espaces(int id_note_espaces) {
        this.id_note_espaces = id_note_espaces;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
    
}
