package services;

import modele.Competence;
import modele.Membre;
import modele.Projet;

import java.util.Collection;

public interface FacadeService {
    public void init();
    public Collection<Projet> competences(Membre m);
    public Collection<Projet> autres(Membre m, Collection<Projet> hascomp);
    public Membre getMembreFromLogin(String login);
    public Projet getProjetFromIntituleP(String intituleP);
    public Collection<Membre> getMembres();
    public Collection<Projet> getProjets();
    public Collection<Competence> getCompetences();
    public Collection<Competence> getAutreComp(Membre m);
    public Competence getCompetenceFromIntituleC(String intituleC);
}
