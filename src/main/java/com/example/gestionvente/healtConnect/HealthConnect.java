package com.example.gestionvente.healtConnect;

import com.example.gestionvente.entites.CategorieProduit;
import com.example.gestionvente.services.CategorieProduitService;
import com.example.gestionvente.services.ProduitService;
import com.example.gestionvente.utils.MyDB;

import java.sql.SQLException;

public class HealthConnect {
    public static void main(String[] args) {

        MyDB db = MyDB.getInstance() ;

        //------------------Categorie&Produit(Service)------------------------
        CategorieProduitService cps=new CategorieProduitService();
        ProduitService ps = new ProduitService();
        CategorieProduit categorieProduit=new CategorieProduit();



        //---------AjoutCategorieProduit-------------
       //CategorieProduit categorieProduit=new CategorieProduit("aaa","bbb");
//        try {
//            cps.ajouter(categorieProduit);
//        }catch (SQLException exception){
//            System.out.println(exception.getMessage());
//        }



        //---------SupprimerCategorieProduit----------
//        boolean catsupp=cps.supprimer(25);
//        if(catsupp){
//            System.out.println("La catégorie a été supprimée avec succès !");
//        }else {
//            System.out.println("erreur");
//        }


        //------ModifierCategorieProduit-----------
//        CategorieProduit categorieProduit=new CategorieProduit();
//        categorieProduit.setID(9);
//        categorieProduit.setNom("Creatine");
//        categorieProduit.setDescription("Proteine");
//        cps.modifier(categorieProduit);

        //---------AffichageCategorieProduit-------------
        try {
            System.out.println(cps.afficher());
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }



        //------------AjouterProduit--------------
//        CategorieProduit categorieProduit=cps.get(26);
//        Produit produit=new Produit("aa","bb","cc",100,categorieProduit);
//        ps.ajouter(produit);

        //-----------SupprimerProduit---------
        //ps.supprimer(30);

        //-----ModifierProduit---------

//        Produit produit=new Produit();
//        produit.setId(18);
//        produit.setNom("Creatine Monohydrate");
//        produit.setDescription("aaaa");
//        produit.setPrix(100f);
//        produit.setImage("aaaaaaaaa.jpg");
//        //produit.setCategory(cps.get(6));
//        produit.setCategory(new CategorieProduit(6,"aaa","bbb"));
//        ps.modifier(produit);


        //-----afficherProduit---------
//        List<Produit> produits = ps.afficher();
//        for (Produit produitt : produits) {
//            System.out.println(produitt);
//        }










    }

}
