package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonnePretBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.ListePretAbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.OuvrageBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.PretBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeController {

    private AbonneBean utilisateurAuthentifie;
    private ListePretAbonneBean listePretAbonneBean;
    private PretBean pret;

    private AbonnePretBean abonne = null;
    private OuvrageBean ouvrage = null;
    private int codeRole = 5;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    @Autowired
    private MicroserviceGestionUtilisateur gestionUtilisateur;

    /**
     * permet de renvoyer sur la page d'emprunt des employés
     * @param model
     * @return
     */
    @RequestMapping(value = "/Emprunt", method = RequestMethod.GET)
    public String employe(Model model){
        //problème thymeleaf
        utilisateurAuthentifie = new AbonneBean();
        pret = new PretBean();
        ouvrage = new OuvrageBean();
        abonne = new AbonnePretBean();
        listePretAbonneBean = new ListePretAbonneBean();

        utilisateurAuthentifie.setPseudo("Régis");

        //ajout dans le model
        model.addAttribute("ouvrage", ouvrage);
        model.addAttribute("abonne", abonne);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        return "Employe";
    }

    /**
     * permet de récupérer l'ouvrage et l'abonné et d'ajouter son prêt dans sa liste
     * @param model
     * @param ouvrage
     * @param abonne
     * @return
     */
    @RequestMapping(value = "/Emprunt", method = RequestMethod.POST)
    public String emprunt(Model model, @ModelAttribute("ouvrage") OuvrageBean ouvrage, @ModelAttribute("abonne") AbonnePretBean abonne){


        //attibution de la date d'emprunt
        Date date = new Date();

        //récupération de l'ouvrage
        ouvrage = livresProxy.ouvrageSelonCodeBibliotheque(ouvrage.getCodeBibliotheque());

        //récupération de l'abonné
        abonne = livresProxy.recupererAbonneSelonNumeroAbonne(abonne.getNumeroAbonne());

        System.out.println("ouvrage récupéré : " + ouvrage);
        System.out.println("abonne récupéré : " + abonne);

        pret.setDateDEmprunt(date);
        pret.setProlongation(false);
        pret.setOuvrage(ouvrage);

        livresProxy.sauvegarderPret(pret);
        livresProxy.sauvegarderAbonnePret(abonne);
        
        /*listePretAbonneBean.setAbonnePret(abonne);
        listePretAbonneBean.setPret(pret);

        livresProxy.ajouterPretAbonne(listePretAbonneBean);*/

        //ajout dans le model
        model.addAttribute("ouvrage", ouvrage);
        model.addAttribute("abonne", abonne);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        return "Employe";
    }

}
