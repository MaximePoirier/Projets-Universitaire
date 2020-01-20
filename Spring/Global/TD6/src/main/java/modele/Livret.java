package modele;



import javax.persistence.Entity;
import java.util.Date;


@Entity
public class Livret extends Compte {
    private double tauxInteret;

    public Livret(Client client, Double solde, Date date, double tauxInteret){
        super(client, solde, date);
        this.tauxInteret = tauxInteret;
    }
}
