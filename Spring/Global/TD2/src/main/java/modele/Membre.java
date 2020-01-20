package modele;

import java.util.ArrayList;
import java.util.Collection;

public class Membre {
    private String login;
    private String motdepasse;
    private String surnom;
    private Collection<Projet> responsable;
    private Collection<Projet> participe;
    private Collection<CompetenceMembre> declare;

    // Constructeurs

    public Membre() {
        this.responsable=new ArrayList<>();
        this.participe=new ArrayList<>();
        this.declare=new ArrayList<>();
    }

    public Membre(String login, String motdepasse, String surnom) {
        this();
        this.login = login;
        this.motdepasse = motdepasse;
        this.surnom = surnom;
    }

    // Getters / setters

    public String getLogin() {
        return login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public Collection<Projet> getResponsable() {
        return responsable;
    }

    public void setResponsable(Collection<Projet> responsable) {
        this.responsable = responsable;
    }

    public Collection<Projet> getParticipe() {
        return participe;
    }

    public void setParticipe(Collection<Projet> participe) {
        this.participe = participe;
    }

    public Collection<CompetenceMembre> getDeclare() {
        return declare;
    }

    public void setDeclare(Collection<CompetenceMembre> declare) {
        this.declare = declare;
    }
}
