package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonnePret;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonnePretOuvrage;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.Ouvrage;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.Pret;
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

    private Abonne utilisateurAuthentifie;
    private Pret pret;
    private Abonne abonne = null;
    private Ouvrage ouvrage = null;
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
        utilisateurAuthentifie = new Abonne();
        pret = new Pret();
        ouvrage = new Ouvrage();
        abonne = new Abonne();

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
    public String emprunt(Model model, @ModelAttribute("ouvrage") Ouvrage ouvrage, @ModelAttribute("abonne") Abonne abonne){

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
        pret.setAbonne(abonne);
        pret.setStatus("non prolongé");

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
