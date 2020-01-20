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
public class FacadeServiceImpl implements FacadeService {
    private Collection<Membre> membres;
    private Collection<Projet> projets;
    private Collection<Competence> competences;

    @Override
    @PostConstruct
    public void init() {
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

        /*CompetenceMembre javafred=new CompetenceMembre(fred,java,4,"java avancé");
        fred.getDeclare().add(javafred);
        java.getCompMembre().add(javafred);*/

        /*CompetenceMembre manafred=new CompetenceMembre(fred,management,4,"tout est sous contrôle");
        fred.getDeclare().add(manafred);
        management.getCompMembre().add(manafred);*/

        /*CompetenceMembre manamatth=new CompetenceMembre(matthieu,management,5,"tout est sous contrôle");
        matthieu.getDeclare().add(manamatth);
        management.getCompMembre().add(manamatth);*/


        allanParson.getNecessite().addAll(competences);
        xion.getNecessite().addAll(competences);
        java.getRequisePour().addAll(projets);
        management.getRequisePour().addAll(projets);

    }

    @Override
    public Collection<Projet> competences(Membre m) {
        // On initialise les projets : tous les projets moins ceux auquel m participe
        Collection<Projet> pcomp=new ArrayList<>();
        pcomp.addAll(projets);
        pcomp.removeAll(m.getResponsable());
        pcomp.removeAll(m.getParticipe());

        // les competences de m
        Collection<Competence> compm=new ArrayList<>();
        for (CompetenceMembre cm:m.getDeclare()) {
            compm.add(cm.getDeType());
        }

        Collection<Projet> res=new ArrayList<>();

        // on ne garde que les projets avec des competences de m
        for (Projet p:pcomp) {
            for (Competence comp:p.getNecessite()) {
                if (compm.contains(comp)) {
                    res.add(p);
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public Collection<Projet> autres(Membre m, Collection<Projet> hascomp) {
        Collection<Projet> pautres=new ArrayList<>();
        pautres.addAll(projets);
        pautres.removeAll(m.getResponsable());
        pautres.removeAll(m.getParticipe());
        pautres.removeAll(hascomp);
        return pautres;
    }

    @Override
    public Membre getMembreFromLogin(String login) {
        for (Membre m:membres) {
            if (m.getLogin().equals(login)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public Projet getProjetFromIntituleP(String intituleP) {
        for (Projet p:projets) {
            if (p.getIntituleP().equals(intituleP)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Collection<Membre> getMembres() {
        return membres;
    }

    @Override
    public Collection<Projet> getProjets() {
        return projets;
    }

    @Override
    public Collection<Competence> getCompetences() {
        return competences;
    }

    @Override
    public Collection<Competence> getAutreComp(Membre m) {
        // les competences de m
        Collection<Competence> compm=new ArrayList<>();
        for (CompetenceMembre cm:m.getDeclare()) {
            compm.add(cm.getDeType());
        }
        Collection<Competence> autreComp = new ArrayList<>();
        autreComp.addAll(competences);
        autreComp.removeAll(compm);
        return autreComp;
    }

    @Override
    public Competence getCompetenceFromIntituleC(String intituleC) {
        for(Competence c : competences){
            if(c.getIntituleC().equals(intituleC)){
                return c;
            }
        }
        return null;
    }
}
