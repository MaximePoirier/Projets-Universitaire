package modele;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Builder
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String nom;
    private String prenom;
    private String adresse;
    @OneToMany(mappedBy = "titulaire")
    private Collection<Compte> comptes;
}
