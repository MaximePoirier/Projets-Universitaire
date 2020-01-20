package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.MainService;

@Controller
@RequestMapping("/controler")
public class MainController {

    @Autowired
    private MainService service;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/home",method = RequestMethod.POST)
    public String home(@RequestParam String login, @RequestParam String password, Model model){
        System.out.println(login + " " +password);
        if(service.checkUser(login,password)){
            model.addAttribute("userName", login);
            return "home";
        }
        else return "login";
    }
}
