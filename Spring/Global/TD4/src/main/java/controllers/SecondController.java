package controllers;


import dto.UtilisateurDto;
import entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import services.MurService;

import java.util.Locale;

@Controller
@SessionAttributes("courant")
@RequestMapping("/")
public class SecondController {
    @Autowired
    MurService murService;


    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        model.addAttribute("user",new UtilisateurDto());
        return "connexion";
    }

    @RequestMapping(value="/connexion")
    public String connexion(@ModelAttribute("user") UtilisateurDto user, Model model) {
            Utilisateur userV = murService.findUtilisateurByLoginPassword(user.getLogin(),user.getPassword());

            if (userV == null) {
                return "connexion";
            } else {
                model.addAttribute("courant", userV.getLogin());
                model.addAttribute("pseudo", userV.getPseudo());
                model.addAttribute("messages",murService.findAllMessage());
                return "mur";
            }
    }


    @GetMapping(value="/rafraichir")
    public String rafraichir(@SessionAttribute("courant") String login,Model model){
        Utilisateur userV = murService.findUtilisateurByLogin(login);
        model.addAttribute("pseudo", userV.getPseudo());
        model.addAttribute("messages",murService.findAllMessage());
        return "mur";
    }

    @PostMapping(value="/ecrire")
    public String ecrire(@SessionAttribute("courant") String login, String message, Model model){
        Utilisateur userV = murService.findUtilisateurByLogin(login);
        murService.createMessage(login,message);

        model.addAttribute("pseudo", userV.getPseudo());
        model.addAttribute("messages",murService.findAllMessage());
        return "mur";
    }


    @PostMapping(value = "/filtrer")
    public String filtrer(@SessionAttribute("courant") String login, String filtre, Model model){
        Utilisateur userV = murService.findUtilisateurByLogin(login);
        model.addAttribute("pseudo", userV.getPseudo());
        model.addAttribute("messages",murService.findMessageByContenu(filtre));
        return "mur";
    }

    @PostMapping(value = "/update")
    public String update(@SessionAttribute("courant") String login, String newlogin, Model model){
        murService.updatePseudo(login, newlogin);

        Utilisateur userV = murService.findUtilisateurByLogin(login);
        model.addAttribute("pseudo", userV.getPseudo());
        model.addAttribute("messages",murService.findAllMessage());
        return "mur";
    }


}

