package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AdresseBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GestionUtilisateurController {

    AbonneBean abonneBean;
    AdresseBean adresseBean;

    @RequestMapping(value = "/Inscription")
    public String inscription(Model model){

        abonneBean = new AbonneBean();
        adresseBean = new AdresseBean();


        model.addAttribute("abonneBean", abonneBean);
        model.addAttribute("adresseBean", adresseBean);

        return "Inscription";
    }

}
