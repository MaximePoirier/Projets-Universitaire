package fr.univ.orleans.aar.tp7.backend.dao;

import fr.univ.orleans.aar.tp7.backend.modele.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<Compte, Long> {
}
