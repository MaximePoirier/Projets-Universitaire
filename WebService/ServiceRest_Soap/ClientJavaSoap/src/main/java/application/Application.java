package application;

import service.Annuaire;
import service.AnnuaireService;

public class Application {
    public static void main(String[] args) {
        AnnuaireService service = new AnnuaireService();
        Annuaire proxy = service.getAnnuairePort();
        System.out.println("liste : "+proxy.getAllNom());
    }
}
