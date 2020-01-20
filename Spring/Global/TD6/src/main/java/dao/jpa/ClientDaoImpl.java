package dao.jpa;

import dao.ClientDao;
import modele.Client;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.Map;

public class ClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {

    public ClientDaoImpl() {
        this(Client.class);
    }
    public ClientDaoImpl(Class<Client> entityClass) {
        super(entityClass);
    }

    public Client findWithComptes(long id){
        EntityGraph graph = getEm().getEntityGraph("graph.client.comptes");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph",graph);

        return getEm().find(getDomainClass(), id, hints);
    }

}
