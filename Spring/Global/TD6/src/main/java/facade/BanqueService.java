package facade;

import modele.Client;
import modele.Compte;
import modele.Livret;

import java.util.Collection;

public interface BanqueService {
    
    void virement(long compteSource, long compteDest, double montant);

    Collection<Client> getAllClients();

    Client getClient(long id);

    Collection<Livret> getAllLivrets();

    Collection<Compte> getAllComptes();

    Collection<Compte> getComptesOfClient(long idClient);

    void saveClients(Client[] clients);

    void deleteClient(long idClient);
}
