package modele;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;


@Entity
@Builder
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Client titulaire;
    private double solde;
    private Date dateOuverture;

}
