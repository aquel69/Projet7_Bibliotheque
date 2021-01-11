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

    @Autowired
    CatalogueController catalogueController;


    AbonneBean abonneAuthentifier = null;

    @RequestMapping(value = "/Authentification", method = RequestMethod.GET)
    public String Authentification(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanGet){

        return "Authentification";
    }

    @RequestMapping(value = "/Authentification",method = RequestMethod.POST )
    public String ValidationAuthentification(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanPost){

        abonneAuthentifier = authentificationUtilisateurProxy.login(abonneBeanPost.getMotDePasse(), abonneBeanPost.getEmail());

        if(abonneAuthentifier.getRole().getCode() != 0){
            catalogueController.accueil(model);

            return "Accueil";
        }else{
            return "Authentification";
        }


    }

}
