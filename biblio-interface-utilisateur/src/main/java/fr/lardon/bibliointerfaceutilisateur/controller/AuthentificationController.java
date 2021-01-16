package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.RoleBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceAuthentificationUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthentificationController {

    @Autowired
    private MicroserviceAuthentificationUtilisateur authentificationUtilisateurProxy;

    @Autowired
    private CatalogueController catalogueController;

    @Autowired
    private GestionUtilisateurController gestionUtilisateurController;

    private int codeRole = 0;
    private AbonneBean utilisateurAuthentifie = null;

    @RequestMapping(value = "/Authentification", method = RequestMethod.GET)
    public String authentification(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanGet){
        RoleBean role = new RoleBean();
        utilisateurAuthentifie = new AbonneBean();
        codeRole = 0;

        //récupération du code role
        if(model.getAttribute("codeRole") != null) codeRole = (int) model.getAttribute("codeRole");
        //récupération du code role
        if(model.getAttribute("utilisateurAuthentifie") != null) utilisateurAuthentifie = (AbonneBean) model.getAttribute("utilisateurAuthentifie");

        //ajout dans le model
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);

        return "Authentification";
    }

    @RequestMapping(value = "/Authentification",method = RequestMethod.POST )
    public String validationAuthentification(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanPost){

        utilisateurAuthentifie = authentificationUtilisateurProxy.login(abonneBeanPost.getMotDePasse(), abonneBeanPost.getEmail());

        if(utilisateurAuthentifie.getRole().getCode() != 0){

            RoleBean role = new RoleBean();

            //récupération du role de l'utilisateur
            role.setCode(utilisateurAuthentifie.getRole().getCode());
            codeRole = role.getCode();

            //ajout dans le model
            model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
            model.addAttribute("codeRole", codeRole);

            //récuperation du catalogue pour l'affichage dans la page accueil
            catalogueController.accueil(model);
            gestionUtilisateurController.modificationCompte(model);

            return "Accueil";
        }else{
            String message = "Le mot de passe ou l'email est incorrect";

            //ajout dans le model
            model.addAttribute("messageErreur", message);
            model.addAttribute("codeRole", codeRole);

            return "Authentification";
        }
    }

    @RequestMapping(value = "/Deconnection", method = RequestMethod.GET)
    public String deconnection(Model model){
        //déconnection de l'utilisateur
        codeRole = 0;
        utilisateurAuthentifie = null;

        //récuperation du catalogue pour l'affichage dans la page accueil
        catalogueController.accueil(model);


        //ajout dans le model
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        //récuperation du catalogue pour l'affichage dans la page accueil
        catalogueController.accueil(model);
        gestionUtilisateurController.modificationCompte(model);


        return "Accueil";
    }

}
