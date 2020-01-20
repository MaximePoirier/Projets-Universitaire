package fr.univ.orleans.aar.tp7.frontend;

import fr.univ.orleans.aar.tp7.backend.dao.ClientRepository;
import fr.univ.orleans.aar.tp7.backend.modele.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LeController {
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/listeClients")
    public String listeDesClients(Model model){
        model.addAttribute("clients", clientRepository.findAll());
        return "afficherListeClients";
    }

    @RequestMapping("/client")
    public String clientParId(@RequestParam(value = "id", required = true) Long id, Model model){
        Optional<Client> client = clientRepository.findById(id);
        model.addAttribute("client", client.get());
        return "afficherClient";
    }
}
