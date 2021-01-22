package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.OuvrageBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.PretBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeController {

    private AbonneBean utilisateurAuthentifie = new AbonneBean();
    private PretBean pretBean = null;
    private AbonneBean abonne = null;
    private OuvrageBean ouvrage = null;
    private int codeRole = 5;

    /**
     * permet de renvoyer sur la page d'emprunt des employés
     * @param model
     * @return
     */
    @RequestMapping(value = "/Emprunt", method = RequestMethod.GET)
    public String employe(Model model){
        //problème thymeleaf
        ouvrage = new OuvrageBean();
        abonne = new AbonneBean();

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
    public String emprunt(Model model, @ModelAttribute("ouvrage") OuvrageBean ouvrage, @ModelAttribute("abonne") AbonneBean abonne){


        //ajout dans le model
        model.addAttribute("ouvrage", ouvrage);
        model.addAttribute("abonne", abonne);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        return "Employe";
    }

}
