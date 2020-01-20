package services;

import dto.MessageDto;
import entities.Message;
import entities.Utilisateur;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MurService {
    @PersistenceContext
    EntityManager em;

    public Utilisateur findUtilisateurByLogin(String login) {
        return em.find(Utilisateur.class,login);
    }

    public Utilisateur findUtilisateurByLoginPassword(String login, String password) {
        Query q=em.createQuery("from Utilisateur u where u.login=:l and u.password=:p");
        q.setParameter("l",login);
        q.setParameter("p",password);
        try {
            return (Utilisateur) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Collection<Utilisateur> findAllUtilisateur() {
        Query q=em.createQuery("from Utilisateur u");
        return q.getResultList();
    }

    public Utilisateur createUtilisateur() {
        // TODO !!!
        return null;
    }


    @Transactional
    public void updatePseudo(String login, String newlogin) {
        Utilisateur u =em.find(Utilisateur.class, login);
        u.setPseudo(newlogin);
        em.merge(u);
    }

    @Transactional
    public void createMessage(String login, String message) {
        Utilisateur u =em.find(Utilisateur.class, login);
        Message msg = new Message();
        msg.setContenu(message);
        msg.setAuteur(u);
        em.persist(msg);
    }

    public Collection<MessageDto> findAllMessage() {
        Collection<MessageDto> messages=new ArrayList<>();

        Query q = em.createQuery("From Utilisateur u Order By u.pseudo");
        Collection<Utilisateur> utils = q.getResultList();

        for(Utilisateur u : utils){
            MessageDto msg = new MessageDto();
            msg.setPseudo(u.getPseudo());

            Collection<String> msgs = new ArrayList<>();
            for(Message m: u.getMessages()){
                msgs.add(m.getContenu());
            }
            msg.setMessages(msgs);
            messages.add(msg);
        }
       /* MessageDto msg=new MessageDto();
        msg.setPseudo("Luc");
        Collection<String> msgs=new ArrayList<>();
        msgs.add("bonjour");
        msgs.add("au revoir");
        msg.setMessages(msgs);
        messages.add(msg);

        msg=new MessageDto();
        msg.setPseudo("Jeannot");
        msgs=new ArrayList<>();
        msgs.add("Je suis un lapin");
        msgs.add("Je mange des carottes");
        msg.setMessages(msgs);
        messages.add(msg);*/

        return messages;
    }

    public Collection<MessageDto> findMessageByContenu(String cont) {
        Collection<MessageDto> messages=new ArrayList<>();

        Query q = em.createQuery("Select u From Utilisateur u "+
                "JOIN u.messages m "+
                "WHERE m.contenu like :motif "+
                "Order by u.pseudo");
        q.setParameter("motif","%"+cont+"%");
        Collection<Utilisateur> utils = q.getResultList();

        Query q2 = em.createQuery("FROM Message m WHERE m.contenu like :motif");
        q2.setParameter("motif","%"+cont+"%");
        Collection<Message> msgFiltrer = q2.getResultList();

        for(Utilisateur u : utils){
            MessageDto msg = new MessageDto();
            msg.setPseudo(u.getPseudo());

            Collection<String> msgs = new ArrayList<>();
            for(Message m: u.getMessages()){
                for(Message m2: msgFiltrer){
                    if(m.getContenu().equals(m2.getContenu())){
                        msgs.add(m.getContenu());
                    }
                }
            }
            msg.setMessages(msgs);
            messages.add(msg);
        }

        return messages;
    }

}
