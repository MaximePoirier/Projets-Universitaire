package modele;


import javax.persistence.*;
import java.util.Collection;


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
    @OneToMany(mappedBy = "titulaire")
    private Collection<Compte> comptes;


    public Client(String nom, String prenom, String adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
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
