package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AdresseBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.BibliothequeBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.RoleBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class GestionUtilisateurController {

    private static final int STRENGTH = 12;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AbonneBean abonneBean = null;
    private AbonneBean abonneSecurisation = null;
    private AdresseBean adresseBean = new AdresseBean();
    private BibliothequeBean bibliothequeBean = null;
    private RoleBean roleBean = null;
    private AbonneBean utilisateurAuthentifie= new AbonneBean();
    AbonneBean abonneAModifier = null;
    private int codeRole = 0;


    @Autowired
    private MicroserviceGestionUtilisateur gestionUtilisateur;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    @Autowired
    private CatalogueController catalogueController;

    @RequestMapping(value = "/Inscription", method = RequestMethod.GET)
    public String inscription(Model model){

        abonneBean = new AbonneBean();
        adresseBean = new AdresseBean();

        //ajout dans le model
        model.addAttribute("abonneBean", abonneBean);
        model.addAttribute("adresseBean", adresseBean);

        return "Inscription";
    }

    @RequestMapping(value = "/Inscription", method = RequestMethod.POST)
    public String validationInscription(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanPost, @ModelAttribute("adresseBean") AdresseBean adresseBeanPost){

        abonneBean = new AbonneBean();
        abonneSecurisation = new AbonneBean();
        adresseBean = new AdresseBean();
        bibliothequeBean = new BibliothequeBean();
        roleBean = new RoleBean();
        bCryptPasswordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        String numeroAbonne = null;
        String motDePasse = null;

        //attribution du role aux abonnés
        roleBean = gestionUtilisateur.recupererRole(1);

        //attribution de la bibliothèque aux abonnés
        bibliothequeBean = gestionUtilisateur.recupererBibliotheque("18004625200177");

        //attribution de la date pour l'ouverture du compte de l'abonnée
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        //attribution du numéro d'abonné
        abonneBean = gestionUtilisateur.recupererDernierAbonne();
        numeroAbonne = abonneBean.getNumeroAbonne();
        int numeroAbonneInt = Integer.parseInt(numeroAbonne) + 1;

        //ajout de l'adresse dans la base de données et récupération de l'id
        gestionUtilisateur.ajouterAdresse(adresseBeanPost);
        adresseBean = gestionUtilisateur.recupererDernierAdresse();

        //sécurisation du mot de passe
        abonneBeanPost.setMotDePasse(bCryptPasswordEncoder.encode(abonneBeanPost.getMotDePasse()));

        //alimentation de l'objet abonné
        abonneBeanPost.setDateDeCreationDuCompte(date);
        abonneBeanPost.setNumeroAbonne(String.valueOf(numeroAbonneInt));
        abonneBeanPost.setBibliotheque(bibliothequeBean);
        abonneBeanPost.setRole(roleBean);
        abonneBeanPost.setAdresse(adresseBean);

        //ajout de l'abonné dans la base de données
        gestionUtilisateur.ajouterAbonne(abonneBeanPost);

        //affichage du message
        String message = abonneBeanPost.getPseudo() + "!! Vous avez créé votre compte !!";
        model.addAttribute("message", message);

        //récupération des livres à afficher dans la page accueil
        catalogueController.accueil(model);

        return "Accueil";

    }

    @RequestMapping(value = "/ModificationCompte", method = RequestMethod.GET)
    public String modificationCompte(Model model){

        abonneAModifier = new AbonneBean();

        //récupération du code role
        if(model.getAttribute("codeRole") != null) codeRole = (int) model.getAttribute("codeRole");
        //récupération du code role
        if(model.getAttribute("utilisateurAuthentifie") != null) utilisateurAuthentifie = (AbonneBean) model.getAttribute("utilisateurAuthentifie");

        abonneAModifier = gestionUtilisateur.recupererAbonne(utilisateurAuthentifie.getIdAbonne());


        //ajout dans le model
        model.addAttribute("abonneAModifier", abonneAModifier);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("adresseBean", adresseBean);

        return "ModificationCompte";

    }

    @RequestMapping(value = "/ModificationCompte", method = RequestMethod.POST)
    public String validationModificationCompte(Model model, @ModelAttribute("abonneAModifier") AbonneBean abonneBeanModifier){

        //modifier utilisateur
        abonneAModifier.setNom(abonneBeanModifier.getNom());
        abonneAModifier.setPrenom(abonneBeanModifier.getPrenom());
        abonneAModifier.setPseudo(abonneBeanModifier.getPseudo());
        abonneAModifier.setEmail(abonneBeanModifier.getEmail());
        abonneAModifier.getAdresse().setNumero(abonneBeanModifier.getAdresse().getNumero());
        abonneAModifier.getAdresse().setComplement(abonneBeanModifier.getAdresse().getComplement());
        abonneAModifier.getAdresse().setRue(abonneBeanModifier.getAdresse().getRue());
        abonneAModifier.getAdresse().setCodePostal(abonneBeanModifier.getAdresse().getCodePostal());
        abonneAModifier.getAdresse().setVille(abonneBeanModifier.getAdresse().getVille());

        gestionUtilisateur.modifierAbonne(abonneAModifier);

        model.addAttribute("abonneAModifier", abonneAModifier);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);

        return "ModificationCompte";
    }

}
