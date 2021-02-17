package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
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

    /**
     * permet de renvoyer sur la page d'emprunt des employés
     * @param model
     * @return
     */
    @RequestMapping(value = "/Emprunt", method = RequestMethod.GET)
    public String employe(Model model){
        utilisateurAuthentifie = new Abonne();
        pret = new Pret();
        ouvrage = new Ouvrage();
        abonne = new Abonne();

        utilisateurAuthentifie.setPseudo("Régis");

        ajoutDansLeModel(model);

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

        if(verificationDEmpruntIdentique(ouvrage)){
            //ajout erreur
            String message = "L'ouvrage est déjà emprunté par cet abonné";

            //ajout dans le model
            model.addAttribute("messageErreur", message);
            ajoutDansLeModel(model);

            return "Employe";
        }

        //alimentation de l'objet prêt
        pret.setDateDEmprunt(localDateTime);
        pret.setDateDeRestitution(localDateTime.plusWeeks(4));
        pret.setProlongation(false);
        pret.setOuvragePret(this.ouvrage);
        pret.setAbonnePret(this.abonne);
        pret.setStatus("non prolongé");

        //sauvegarder le prêt
        livresProxy.sauvegarderPret(pret);

        //permet d'empêcher le remplissage des champs automatiquement après validation
        this.ouvrage.setCodeBibliotheque("");
        this.abonne.setNumeroAbonne("");

        ajoutDansLeModel(model);

        return "Employe";
    }


    @RequestMapping(value = "/Restitution", method = RequestMethod.POST)
    public String restitution(Model model, @ModelAttribute("ouvrage") Ouvrage ouvrage, @ModelAttribute("abonne") Abonne abonne){
        AbonnePretOuvrage abonnePretOuvrage;
        List<Pret> pretList;

        //récupération de l'ouvrage
        this.ouvrage = livresProxy.ouvrageSelonCodeBibliotheque(ouvrage.getCodeBibliotheque());

        //récupération de l'abonné
        this.abonne = livresProxy.recupererAbonneSelonNumeroAbonne(abonne.getNumeroAbonne());

        //récupération de la liste des prêt
        abonnePretOuvrage = livresProxy.abonnePretSelonSonId(this.abonne.getIdAbonne());
        pretList = abonnePretOuvrage.getListePret();

        restitutionDeLOuvrage(pretList);

        //permet d'empêcher le remplissage des champs automatiquement après validation
        this.ouvrage.setCodeBibliotheque("");
        this.abonne.setNumeroAbonne("");

        ajoutDansLeModel(model);

        return "Employe";
    }

    private boolean verificationDEmpruntIdentique(Ouvrage ouvrage) {
        AbonnePretOuvrage abonnePretOuvrage;
        List<Ouvrage> ouvrageList;
        List<Pret> pretList;

        //récupération de la liste des prêt
        abonnePretOuvrage = livresProxy.abonnePretSelonSonId(this.abonne.getIdAbonne());
        pretList = abonnePretOuvrage.getListePret();

        for(Pret pret : pretList){
            if(pret.getOuvragePret().getCodeBibliotheque().equals(ouvrage.getCodeBibliotheque()) && pret.isRendu() == false){
                return true;
            }
        }

        return false;
    }

    public void restitutionDeLOuvrage(List<Pret> pretList){
        //attibution de la date de restitution
        LocalDateTime localDateTime = LocalDateTime.now();

        for(Pret pretDeLaListe : pretList){
            if(pretDeLaListe.getOuvragePret().getCodeBibliotheque().equals(ouvrage.getCodeBibliotheque())){
                //récupération du prêt à modifier
                PretAModifie pretAModifie = livresProxy.pretAModifieSelonSonId(pretDeLaListe.getIdPret());

                //modification du prêt
                pretAModifie.setStatus("Rendu");
                pretAModifie.setRendu(true);
                pretAModifie.setDateDeRestitution(localDateTime);
                pretAModifie.setDateDEmprunt(pretDeLaListe.getDateDEmprunt());

                //sauvegarder le prêt
                livresProxy.sauvegardePretAModifie(pretAModifie);
            }
        }
    }

    public void ajoutDansLeModel(Model model){
        model.addAttribute("ouvrage", this.ouvrage);
        model.addAttribute("abonne", this.abonne);
        model.addAttribute("codeRole", this.codeRole);
        model.addAttribute("utilisateurAuthentifie", this.utilisateurAuthentifie);
    }

}
