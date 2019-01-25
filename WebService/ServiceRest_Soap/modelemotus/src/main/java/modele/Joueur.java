package modele;

public class Joueur {

    private String nom;

    public Joueur() {
    }

    public Joueur(String nom) {
        this();
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
