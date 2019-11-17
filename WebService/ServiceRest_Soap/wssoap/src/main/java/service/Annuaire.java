package service;

import exceptions.PersonneNotFoundException;
import modele.Personne;

import javax.jws.WebService;
import java.util.Collection;
import java.util.HashMap;

@WebService
public class Annuaire {
    private static HashMap<String, Personne> annuaire;
    static {
        annuaire = new HashMap<String,Personne>();
        annuaire.put("moal", new Personne("moal","frederic","0238000000"));
        annuaire.put("exbrayat", new Personne("exbrayat","matthieu","0238000000"));
    };
    public String searchTelephone(String nom) throws PersonneNotFoundException {
        Personne p =  annuaire.get(nom);
        if (p==null) {
            throw new PersonneNotFoundException();
        }
        return p.getTelephone();
    }
    public void addPersonne(Personne p) {
        annuaire.put(p.getNom(), p);
    }
    public void addPersonneList(String nom, String prenom, String tel) {
        Personne p = new Personne(nom,prenom,tel);
        annuaire.put(p.getNom(), p);
    }
    public Collection<String> getAllNom() {
        return annuaire.keySet();
    }
}
