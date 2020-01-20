package services;

import modele.Competence;
import modele.CompetenceMembre;
import modele.Membre;
import modele.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ControleServiceImpl implements ControleService {

    @Autowired
    private FacadeService facadeServiceImpl;

    @Override
    public Collection<Membre> checkBase() {
        Collection<Membre> coupables = new ArrayList<>();
        for(Projet p :  facadeServiceImpl.getProjets()){
            for(Membre m : p.getContributionDe()){
                int nbComp = 0;
                Collection<Competence> listComp = new ArrayList<>();
                for (CompetenceMembre cm : m.getDeclare()){
                    listComp.add(cm.getDeType());
                }
                for (Competence c : p.getNecessite()){
                    if(listComp.contains(c)){
                        nbComp++;
                    }
                }
                if(nbComp == 0){
                    if(!coupables.contains(m)){
                        coupables.add(m);
                    }
                }
            }
            int nbComp = 0;
            Collection<Competence> listComp = new ArrayList<>();
            for(CompetenceMembre cm : p.getDirigePar().getDeclare()){
                listComp.add(cm.getDeType());
            }
            for (Competence c : p.getNecessite()){
                if(listComp.contains(c)){
                    nbComp++;
                }
            }
            if (nbComp == 0){
                if(!coupables.contains(p.getDirigePar())){
                    coupables.add(p.getDirigePar());
                }
            }
        }
        return coupables;
    }
}
