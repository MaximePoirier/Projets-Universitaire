package modele;

import java.util.ArrayList;
import java.util.Collection;

public class Competence {

    private String intituleC;
    private String descriptionC;
    private Collection<Projet> requisePour;
    private Collection<CompetenceMembre> compmembre;

    // Constructeurs

    public Competence() {
        this.requisePour=new ArrayList<>();
        this.compmembre = new ArrayList<>();
    }

    public Competence(String intituleC, String descriptionC) {
        this();
        this.intituleC = intituleC;
        this.descriptionC = descriptionC;
    }

    // Getters et setters

    public String getIntituleC() {
        return intituleC;
    }

    public void setIntituleC(String intituleC) {
        this.intituleC = intituleC;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public Collection<Projet> getRequisePour() {
        return requisePour;
    }

    public void setRequisePour(Collection<Projet> requisePour) {
        this.requisePour = requisePour;
    }

    public Collection<CompetenceMembre> getCompmembre() {
        return compmembre;
    }

    public void setCompmembre(Collection<CompetenceMembre> compmembre) {
        this.compmembre = compmembre;
    }
}
