package controllers;

import modele.Pseudo;
import services.ProxyService;
import services.exceptions.BadTokenException;
import vue.Menu;


public class MotusController {
    private Pseudo pseudo;
    private String token;

    Menu menu;

    private String authUri;

    public MotusController() {
        this.pseudo = new Pseudo();
        this.authUri = null;
        this.token = null;
        menu = new Menu(this);
        menu.afficher();
    }

    public void demandeToken(String login, String password){
        if(this.authUri != null){
            this.token = ProxyService.getToken(login, password, this.authUri);
        }
        else {
            System.out.println("Lancez d'abord une requete pour connaitre le serveur d'authentification");
        }
    }

    public void connexion(String pseudo){
        this.pseudo.setPseudo(pseudo);
        try {
            System.out.println(ProxyService.connexion(this.pseudo, this.token));
        } catch (BadTokenException e) {
            this.authUri = e.getAuthUri();
        }
    }

    public void demmarer(String dico){
        try {
            System.out.println(ProxyService.demmarer(dico, this.pseudo.getPseudo(), this.token));
        } catch (BadTokenException e) {
            this.authUri = e.getAuthUri();
        }
    }


    public void getDico(){
        try {
             System.out.println(ProxyService.getDico(this.token));
        } catch (BadTokenException e) {
            this.authUri = e.getAuthUri();
        }
    }

    public void essaie(String mot){
        try {
            System.out.println(ProxyService.essaie(mot, this.pseudo.getPseudo(), this.token));
        } catch (BadTokenException e) {
            this.authUri = e.getAuthUri();
        }
    }

    public void getEssaies(){
        try {
            System.out.println(ProxyService.getEssaies(this.pseudo.getPseudo(), this.token));
        } catch (BadTokenException e) {
            this.authUri = e.getAuthUri();
        }
    }

    public void deconnecter(){
        try {
            System.out.println(ProxyService.deconnecter(this.pseudo.getPseudo(), this.token));
        } catch (BadTokenException e) {
            this.authUri = e.getAuthUri();
        }
    }
}
