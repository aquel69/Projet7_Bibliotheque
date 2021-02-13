package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonnePretBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonnePretOuvrageBean;
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

import java.time.LocalDateTime;

@Controller
public class EmployeController {

    private AbonneBean utilisateurAuthentifie;
    private PretBean pret;
    private AbonnePretOuvrageBean abonne = null;
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
        abonne = new AbonnePretOuvrageBean();

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
        LocalDateTime localDateTime = LocalDateTime.now();

        //récupération de l'ouvrage
        ouvrage = livresProxy.ouvrageSelonCodeBibliotheque(ouvrage.getCodeBibliotheque());

        //récupération de l'abonné
        abonne = livresProxy.recupererAbonneSelonNumeroAbonne(abonne.getNumeroAbonne());

        //alimentation de l'objet prêt
        pret.setDateDEmprunt(localDateTime);
        pret.setDateDeRestitution(localDateTime.plusWeeks(4));
        pret.setProlongation(false);
        pret.setOuvragePret(ouvrage);
        pret.setAbonnePret(abonne);

        //sauvegarder le prêt
        livresProxy.sauvegarderPret(pret);

        //ajout dans le model
        model.addAttribute("ouvrage", ouvrage);
        model.addAttribute("abonne", abonne);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        return "Employe";
    }

}
