package fr.univ.orleans.aar.tp7.backend.dao;

import fr.univ.orleans.aar.tp7.backend.modele.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client,Long> {
}
