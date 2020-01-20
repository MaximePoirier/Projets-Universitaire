package services;


import modele.Competence;
import modele.CompetenceMembre;
import modele.Membre;
import modele.Projet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MainService {
    private Collection<Membre> membres;
    private Collection<Projet> projets;
    private Collection<Competence> competences;

    @PostConstruct
    public void init(){
        membres=new ArrayList<>();
        Membre matthieu=new Membre("Matthieu","Matthieu","Matthieu");
        membres.add(matthieu);
        Membre fred=new Membre("Fred","Fred","Fred");
        membres.add(fred);

        projets=new ArrayList<>();
        Projet allanParson=new Projet("Allan Parson","Un projet musical");
        allanParson.setDirigePar(matthieu);
        matthieu.getResponsable().add(allanParson);
        projets.add(allanParson);
        Projet xion=new Projet("Xion","Pour partager une vision");
        xion.setDirigePar(fred);
        fred.getResponsable().add(xion);
        projets.add(xion);

        competences = new ArrayList<>();
        Competence java=new Competence("Java","POO");
        competences.add(java);
        Competence management=new Competence("Management", "Gestion d'équipe, résolution de conflits");
        competences.add(management);

        CompetenceMembre javamatth=new CompetenceMembre(matthieu,java,3,"java avancé");
        matthieu.getDeclare().add(javamatth);
        java.getCompMembre().add(javamatth);
        CompetenceMembre manafred=new CompetenceMembre(fred,management,4,"tout est sous contrôle");
        fred.getDeclare().add(manafred);
        management.getCompMembre().add(manafred);


        allanParson.getNecessite().addAll(competences);
        xion.getNecessite().addAll(competences);
        java.getRequisePour().addAll(projets);
        management.getRequisePour().addAll(projets);
    }

    public Boolean checkUser(String login, String password){
        for (Membre membre: membres) {
            if(login.equals(membre.getLogin())){
                if(password.equals(membre.getMotdepasse())){
                    return true;
                }
            }
        }
        return false;
    }
}
