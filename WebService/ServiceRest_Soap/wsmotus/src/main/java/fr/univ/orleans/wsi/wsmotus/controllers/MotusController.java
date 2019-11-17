package fr.univ.orleans.wsi.wsmotus.controllers;



import exceptions.MaxNbCoupsException;
import exceptions.MotInexistantException;
import exceptions.PseudoDejaPrisException;
import exceptions.PseudoNonConnecteException;
import facade.FacadeMotus;
import facade.FacadeMotusStatic;
import fr.univ.orleans.wsi.wsmotus.modele.Pseudo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/motus")
@CrossOrigin("*")


public class MotusController {

    private final static FacadeMotus facadeMotus = new FacadeMotusStatic();

    @RequestMapping(value = "/partie",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})

    public ResponseEntity<String> connexion(@RequestBody Pseudo pseudo){
        try{
            if (pseudo.getPseudo() != null && !pseudo.getPseudo().isEmpty() && pseudo.getPseudo().length()>2) {
                facadeMotus.connexion(pseudo.getPseudo());
                return ResponseEntity.created(new URI("/partie/"+pseudo)).build();
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("pseudo invalide : il doit contenir plus de 2 caractères");
        }catch (PseudoDejaPrisException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pseudo déjà pris");
        }catch (URISyntaxException E){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(E.getMessage());
        }
    }

    @RequestMapping(value = "/dico", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})

    public ResponseEntity<Collection<String>> getDico(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facadeMotus.getListeDicos());
    }


    @RequestMapping(value = "/partie/{pseudo}",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})

    public ResponseEntity<String> demmarer(@PathVariable String pseudo, @RequestParam String dico ){
        try{
            facadeMotus.nouvellePartie(pseudo,dico);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Vous pouvez commencer à jouer :");
        }catch (PseudoNonConnecteException E ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vous n'êtes pas connecté(e), veuillez vous connecter pour commencer à jouer ");
        }
    }

    @RequestMapping(value = "/partie/{pseudo}",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})

    public ResponseEntity<String> essaie(@PathVariable String pseudo,@RequestParam String mot){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(facadeMotus.jouer(pseudo,mot));
        }catch(PseudoNonConnecteException E){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vous n'êtes pas connecté(e), veuillez vous connecter pour commencer à jouer ");
        }catch(MaxNbCoupsException E){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perdu ! Nombre de coups maximum atteint ! Try again !");
        }catch (MotInexistantException E){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mot inexistant dans le dictionnaire");
        }
    }

    @RequestMapping(value = "/partie/{pseudo}/essaies",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})

    public ResponseEntity<Collection<String>> getEssaies(@PathVariable String pseudo){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(facadeMotus.getPartie(pseudo).getEssais());
        }catch (PseudoNonConnecteException E){
            ResponseEntity<Collection<String>> body = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Arrays.asList("Vous n'êtes pas connecté(e), veuillez vous connecter pour commencer à jouer "));
            return body;
        }
    }

    @RequestMapping(value = "/partie/{pseudo}",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<String> deconnexion(@PathVariable String pseudo){
        try{
            facadeMotus.deconnexion(pseudo);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Déconnecté !");
        }catch(PseudoNonConnecteException E){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vous n'êtes pas connecté(e), veuillez vous connecter pour commencer à jouer ");
        }
    }


    @RequestMapping(value="/partie/{pseudo}/reponse",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<String> reponse(@PathVariable String pseudo){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(facadeMotus.getPartie(pseudo).getMotRecherche());
        }catch (PseudoNonConnecteException E){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vous n'êtes pas connecté(e), veuillez vous connecter pour commencer à jouer ");
        }
    }

}
