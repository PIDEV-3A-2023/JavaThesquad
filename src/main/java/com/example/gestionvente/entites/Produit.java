package com.example.gestionvente.entites;

public class Produit {
    private int id ;
    private String nom,description,image;
    private float prix;
    private CategorieProduit category;


    public Produit() {
    }

    public Produit(String nom, String description, String image, float prix, CategorieProduit category) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.category = category;
    }

    public Produit(int id, String nom, String description, String image, float prix, CategorieProduit category) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public CategorieProduit getCategory() {
        return category;
    }

    public void setCategory(CategorieProduit category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", prix=" + prix +
                ", category=" + category +
                '}';
    }
}
