package fr.univ.orleans.aar.tp7;

import fr.univ.orleans.aar.tp7.backend.dao.ClientRepository;
import fr.univ.orleans.aar.tp7.backend.dao.CompteRepository;
import fr.univ.orleans.aar.tp7.backend.dao.LivretRepository;
import fr.univ.orleans.aar.tp7.backend.modele.Client;
import fr.univ.orleans.aar.tp7.backend.modele.Compte;
import fr.univ.orleans.aar.tp7.backend.modele.Livret;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Tp7Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp7Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ClientRepository clientRepository, CompteRepository compteRepository, LivretRepository livretRepository, PasswordEncoder passwordEncoder){
        return (args) -> {
            String mdp = "abcd";
            String mdpEnc = passwordEncoder.encode(mdp);
            char [] pass = mdpEnc.toCharArray();
            Client c1 = new Client( "Martin", "Paul", "Orléans", pass,"ADMIN");
            Client c2 = new Client( "Dupont", "Sylvie", "Olivet",pass,"USER");
            Client c3 = new Client( "Dupond", "Henri", "La ferté",pass,"USER");

            Compte co1 = new Compte(c1,2300.0,new SimpleDateFormat("dd/MM/yy").parse("31/01/2010"));
            Compte co2 = new Compte(c2,5440.0,new SimpleDateFormat("dd/MM/yy").parse("05/07/2001"));
            Livret l1 = new Livret(c2, 655.0,new SimpleDateFormat("dd/MM/yy").parse("05/07/2011"),0.05);
            Compte co3 = new Compte(c3, 450.0,new SimpleDateFormat("dd/MM/yy").parse("25/12/2013"));

            c1.addCompte(co1);
            c2.addCompte(co2);
            c2.addCompte(l1);
            c3.addCompte(co3);

            clientRepository.save(c1);
            clientRepository.save(c2);
            clientRepository.save(c3);

        };
    }

}
