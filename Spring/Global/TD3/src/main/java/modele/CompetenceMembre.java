package modele;

public class CompetenceMembre {
    private Membre declareePar;
    private Competence deType;
    private int niveau;
    private String commentaire;

    // Constructeurs

    public CompetenceMembre(Membre declareePar, Competence deType, int niveau, String commentaire) {
        this.declareePar = declareePar;
        this.deType = deType;
        this.niveau = niveau;
        this.commentaire = commentaire;
    }

    // Getters et setters

    public Membre getDeclareePar() {
        return declareePar;
    }

    public void setDeclareePar(Membre declareePar) {
        this.declareePar = declareePar;
    }

    public Competence getDeType() {
        return deType;
    }

    public void setDeType(Competence deType) {
        this.deType = deType;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
