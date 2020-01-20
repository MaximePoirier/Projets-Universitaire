package fr.univ.orleans.aar.tp7.backend.modele;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;


@Entity
@NamedEntityGraph(name = "graph.client.comptes",
                    attributeNodes = @NamedAttributeNode("comptes"))
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String nom;
    private String prenom;
    private String adresse;
    @OneToMany(mappedBy = "titulaire",cascade = CascadeType.ALL)
    private Collection<Compte> comptes;
    private char[] password;
    private boolean active;
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "client_roles")
    private Set<String> roles;

    public Client() {
        comptes = new ArrayList<Compte>();
        this.roles = new HashSet<String>();
    }

    public Client(String nom, String prenom, String adresse, char[] password, String role){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        comptes = new ArrayList<Compte>();
        this.password = password;
        this.active = true;
        this.roles = new HashSet<String>();
        roles.add(role);
    }

    public void addCompte(Compte compte){
        this.comptes.add(compte);
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public long getId(){
        return this.id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }
}
