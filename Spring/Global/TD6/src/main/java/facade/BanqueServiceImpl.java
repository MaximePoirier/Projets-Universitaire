package facade;

import dao.ClientDao;
import dao.CompteDao;
import dao.LivretDao;
import modele.Client;
import modele.Compte;
import modele.Livret;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = true)
public class BanqueServiceImpl implements BanqueService {
    private ClientDao clientDao;
    private CompteDao compteDao;
    private LivretDao livretDao;

    public BanqueServiceImpl(ClientDao clientDao, CompteDao compteDao, LivretDao livretDao) {
        this.clientDao = clientDao;
        this.compteDao = compteDao;
        this.livretDao = livretDao;
    }

    @Transactional(readOnly = false)
    @Override
    public void virement(long compteSource, long compteDest, double montant) {
        Compte source = compteDao.find(compteSource);
        Compte dest = compteDao.find(compteDest);
        if(source.getSolde() - montant >= 0){

        }

        source.setSolde(source.getSolde() - montant);
        dest.setSolde(dest.getSolde() + montant);

        compteDao.edit(source);
        compteDao.edit(dest);
    }


    @Override
    public Collection<Client> getAllClients() {
        return clientDao.findAll();
    }

    @Override
    public Client getClient(long id) {
        return clientDao.find(id);
    }

    @Override
    public Collection<Livret> getAllLivrets() {
        return livretDao.findAll();
    }

    @Override
    public Collection<Compte> getAllComptes() {
        return compteDao.findAll();
    }

    @Override
    public Collection<Compte> getComptesOfClient(long idClient) {
        Client client  = clientDao.findWithComptes(idClient);
        return client.getComptes();
    }

    @Transactional(readOnly = false)
    @Override
    public void saveClients(Client[] clients) {
        for(Client c : clients){
            clientDao.create(c);
        }

    }

    @Transactional(readOnly = false)
    @Override
    public void deleteClient(long idClient) {
        Client c = clientDao.find(idClient);
        if( c == null) throw new RuntimeException("Le client id : "+ idClient + " n'existe pas");
        clientDao.remove(c);
    }
}
