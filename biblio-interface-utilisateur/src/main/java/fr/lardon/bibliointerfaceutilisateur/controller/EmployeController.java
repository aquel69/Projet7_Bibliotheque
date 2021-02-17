package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

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
        this.ouvrage = livresProxy.ouvrageSelonCodeBibliotheque(ouvrage.getCodeBibliotheque());

        //récupération de l'abonné
        this.abonne = livresProxy.recupererAbonneSelonNumeroAbonne(abonne.getNumeroAbonne());

        System.out.println(ouvrage);
        System.out.println(this.ouvrage);

        //alimentation de l'objet prêt
        pret.setDateDEmprunt(localDateTime);
        pret.setDateDeRestitution(localDateTime.plusWeeks(4));
        pret.setProlongation(false);
        pret.setOuvragePret(this.ouvrage);
        pret.setAbonnePret(this.abonne);
        pret.setStatus("non prolongé");

        //sauvegarder le prêt
        livresProxy.sauvegarderPret(pret);

        this.ouvrage.setCodeBibliotheque("");
        this.abonne.setNumeroAbonne("");

        //ajout dans le model
        model.addAttribute("ouvrage", this.ouvrage);
        model.addAttribute("abonne", this.abonne);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        return "Employe";
    }

    @RequestMapping(value = "/Restitution", method = RequestMethod.POST)
    public String restitution(Model model, @ModelAttribute("ouvrage") Ouvrage ouvrage, @ModelAttribute("abonne") Abonne abonne){
        AbonnePretOuvrage abonnePretOuvrage = new AbonnePretOuvrage();
        List<Pret> pretList = null;

        //attibution de la date de restitution
        LocalDateTime localDateTime = LocalDateTime.now();

        //récupération de l'ouvrage
        this.ouvrage = livresProxy.ouvrageSelonCodeBibliotheque(ouvrage.getCodeBibliotheque());

        //récupération de l'abonné
        this.abonne = livresProxy.recupererAbonneSelonNumeroAbonne(abonne.getNumeroAbonne());

        //récupération de la liste des prêt
        abonnePretOuvrage = livresProxy.abonnePretSelonSonId(this.abonne.getIdAbonne());
        pretList = abonnePretOuvrage.getListePret();

        for(Pret pretDeLaListe : pretList){
            if(pretDeLaListe.getOuvragePret().getCodeBibliotheque().equals(ouvrage.getCodeBibliotheque())){
                PretAModifie pretBoucle;
                pretBoucle = livresProxy.pretAModifieSelonSonId(pretDeLaListe.getIdPret());

                System.out.println("pret avant " + pretBoucle);

                pretBoucle.setStatus("Rendu");
                pretBoucle.setRendu(true);
                pretBoucle.setDateDeRestitution(localDateTime);
                pretBoucle.setDateDEmprunt(pretDeLaListe.getDateDEmprunt());

                System.out.println("pret apres " + pretBoucle);

                //sauvegarder le prêt
                livresProxy.sauvegardePretAModifie(pretBoucle);
            }
        }



        this.ouvrage.setCodeBibliotheque("");
        this.abonne.setNumeroAbonne("");

        model.addAttribute("abonne", this.abonne);
        model.addAttribute("ouvrage", this.ouvrage);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        return "Employe";
    }

}
