package dao;

import modele.Client;

public interface ClientDao extends AbstractDao<Client> {
    public Client findWithComptes(long id);
}
