package fr.alardon.biblio_abonneui.controller;

import fr.alardon.biblio_abonneui.models.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AbonneController {



    @RequestMapping(value = "/")
    public String accueil(Model model){
        //essai
        Test test = new Test();
        String message = test.getMessage();
        model.addAttribute("message", message);

        return "Accueil";
    }

}
