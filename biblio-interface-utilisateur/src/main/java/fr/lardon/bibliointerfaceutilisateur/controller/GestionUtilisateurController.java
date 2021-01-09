package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AdresseBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.BibliothequeBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.RoleBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.LivreBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.OuvrageBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class GestionUtilisateurController {

    private AbonneBean abonneBean = null;
    private AdresseBean adresseBean = null;
    private BibliothequeBean bibliothequeBean = null;
    private RoleBean roleBean = null;

    @Autowired
    private MicroserviceGestionUtilisateur gestionUtilisateur;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    @RequestMapping(value = "/Inscription", method = RequestMethod.GET)
    public String inscription(Model model){

        abonneBean = new AbonneBean();
        adresseBean = new AdresseBean();


        model.addAttribute("abonneBean", abonneBean);
        model.addAttribute("adresseBean", adresseBean);

        return "Inscription";
    }

    @RequestMapping(value = "/Inscription", method = RequestMethod.POST)
    public String validationInscription(Model model, @ModelAttribute("abonneBean") AbonneBean abonneBeanPost, @ModelAttribute("adresseBean") AdresseBean adresseBeanPost){

        abonneBean = new AbonneBean();
        adresseBean = new AdresseBean();
        bibliothequeBean = new BibliothequeBean();
        roleBean = new RoleBean();
        String numeroAbonne = null;

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

        //alimentation de l'objet abonné
        abonneBeanPost.setDateDeCreationDuCompte(date);
        abonneBeanPost.setNumeroAbonne(String.valueOf(numeroAbonneInt));
        abonneBeanPost.setBibliotheque(bibliothequeBean);
        abonneBeanPost.setRole(roleBean);
        abonneBeanPost.setAdresse(adresseBean);

        //ajout de l'abonné dans la base de données
        gestionUtilisateur.ajouterAbonne(abonneBeanPost);

        System.out.println("adresse " + adresseBeanPost.toString());
        System.out.println(" abonné " + abonneBeanPost.toString());

        String message = abonneBeanPost.getPseudo() + "!! Vous avez créé votre compte !!";

        model.addAttribute("message", message);

        int index = 0;
        List<LivreBean> livres;

        List<OuvrageBean> ouvragesNouveaute;
        ArrayList<LivreBean> livreTop;
        List<OuvrageBean> ouvragesPremierePartie = new ArrayList<>();
        List<OuvrageBean> ouvragesSecondePartie = new ArrayList<>();

        //récupération du top 10
        livres = livresProxy.topLivre();
        livreTop = new ArrayList<>(livres.subList(0, 10));

        //récupération des nouveautés
        ouvragesNouveaute = livresProxy.listeOuvrageNouveaute();

        for(OuvrageBean ouvrage : ouvragesNouveaute){
            if(index < 3)
                ouvragesPremierePartie.add(ouvrage);
            else if(index < 6)
                ouvragesSecondePartie.add(ouvrage);

            index++;
        }

        /*model.addAttribute("message", message);*/
        model.addAttribute("livres", livreTop);
        model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
        model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);

        return "Accueil";
    }

}
