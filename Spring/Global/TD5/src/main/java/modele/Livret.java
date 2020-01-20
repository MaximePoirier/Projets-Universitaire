package modele;

import lombok.Builder;

import javax.persistence.Entity;

@Builder
@Entity
public class Livret extends Compte {
    private double tauxInteret;
}
