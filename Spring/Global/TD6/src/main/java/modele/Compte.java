package modele;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Client titulaire;
    private double solde;
    private Date dateOuverture;

    public Compte(Client client, Double solde, Date date){
        this.titulaire = client;
        this.solde = solde;
        this.dateOuverture = date;
    }

    public Long getId(){
        return this.id;
    }

    public double getSolde(){
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
