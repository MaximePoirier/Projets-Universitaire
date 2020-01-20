package run;

import modele.Client;

import javax.persistence.*;

public class Run {



    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banquePU");
        EntityManager em = emf.createEntityManager();

        Client c1 = Client.builder()
                .nom("poirier")
                .prenom("maxime")
                .build();


        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(c1);
        transaction.commit();






    }
}
