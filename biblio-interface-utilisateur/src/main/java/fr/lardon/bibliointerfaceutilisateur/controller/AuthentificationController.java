package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.RoleBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceAuthentificationUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /**
     * permet d'afficher la page d'authentification
     * @param model
     * @param abonneBeanGet
     * @return
     */
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

    /**
     * permet de récupérer les donnéees saisies par l'utilisateur et de vérifier si l'authentification est valide
     * si elle l'est l'utilisateur est renvoyé sur la page d'accueil sinon il reste sur la page authentification
     * @param model
     * @param abonneBeanPost
     * @return
     */
    @RequestMapping(value = "/Authentification",method = RequestMethod.POST )
    public String validationAuthentification(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanPost){

        //verification du log / récupération du code role et de l'id et du pseudo de l'abonné / si codeRole = 0 envoi message du message d'erreur
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

    /**
     * permet de déconnecter l'utilisateur et de renvoyer sur la page d'accueil
     * @param model
     * @return
     */
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
