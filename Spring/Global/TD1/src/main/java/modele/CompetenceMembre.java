package modele;

import java.util.ArrayList;
import java.util.Collection;

public class CompetenceMembre {

    private int niveau;
    private String commentaire;
    private Competence deType;
    private Membre declareePar;

    //Constructeurs
    public CompetenceMembre(int niveau, String commentaire){
        this.niveau = niveau;
        this.commentaire = commentaire;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Competence getDeType() {
        return deType;
    }


    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDeType(Competence deType) {
        this.deType = deType;
    }


    public Membre getDeclareePar() {
        return declareePar;
    }

    public void setDeclareePar(Membre declareePar) {
        this.declareePar = declareePar;
    }
}
