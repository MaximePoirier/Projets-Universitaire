package vue;

import controllers.MotusController;

import java.util.Scanner;

public class Menu {
    private MotusController controller;

    public Menu(MotusController controller){
        this.controller = controller;
    }

    public void afficher(){
        Scanner sc = new Scanner(System.in);
        int in = -1;
        while(in != 0){
            System.out.println("Tapez 1 pour choisir un pseudo");
            System.out.println("Tapez 2 pour voir les dictionnaires disponibles ");
            System.out.println("Tapez 3 pour commencer à jouer");
            System.out.println("Tapez 4 pour vous déconnecter ");
            System.out.println("Tapez 5 pour demander un Token");
            System.out.println("Tapez 0 pour quitter ");
            in=sc.nextInt();
            switch (in){
                case 1 :
                    System.out.println("Entrez votre pseudo pour jouer : ");
                    String pseudo = sc.next();
                    controller.connexion(pseudo);
                    break;

                case 2 :
                    controller.getDico();
                    break;

                case 3 :
                    System.out.println("Entrez le dictionnaire choisi pour commencer à jouer : ");
                    controller.demmarer(sc.next());
                    String mot="";
                    while(!mot.equals("exit")){
                        System.out.println("Entrez un mot ou tapez 1 pour voir les mots déjà essayés ou tapez exit pour quitter la partie : ");
                        mot=sc.next();
                        if(mot.equals("1")){
                            controller.getEssaies();
                        }
                        else if(mot.equals("exit")){

                        }
                        else{
                            controller.essaie(mot);
                        }
                    }
                    break;

                case 4 :
                    controller.deconnecter();
                    System.out.println("Entrez de nouveau un pseudo pour recommencer ou tapez 0 pour quitter :");
                    break;

                case 5 :
                    System.out.println("Entrez votre login de connexion : ");
                    String login = sc.next();
                    System.out.println("Entrez votre mot de passe :");
                    String password = sc.next();
                    controller.demandeToken(login, password);
                    break;

            }
        }
    }
}
