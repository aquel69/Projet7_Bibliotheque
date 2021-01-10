package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceAuthentificationUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthentificationController {

    @Autowired
    MicroserviceAuthentificationUtilisateur authentificationUtilisateurProxy;

    AbonneBean abonneRetourner = new AbonneBean();

    @RequestMapping(value = "/Authentification", method = RequestMethod.GET)
    public String Authentification(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanGet){

        return "Authentification";
    }

    @RequestMapping(value = "/Authentification",method = RequestMethod.POST )
    public String ValidationAuthentification(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanPost){

        abonneRetourner = authentificationUtilisateurProxy.login(abonneBeanPost.getMotDePasse(), abonneBeanPost.getEmail());
        System.out.println("abonne Role " + abonneRetourner.getRole().getCode() + "abonne Pseudo " + abonneRetourner.getPseudo());

        return "Accueil";
    }

}
