package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Role;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogueController {
    private List<Ouvrage> ouvragesNouveaute;
    private ArrayList<Livre> livreTop;
    private List<Ouvrage> ouvragesPremierePartie = new ArrayList<>();
    private List<Ouvrage> ouvragesSecondePartie = new ArrayList<>();
    private List<Livre> livres;
    private AbonnePretOuvrage abonnePret;
    private Role role = new Role();
    private Abonne utilisateurAuthentifie = new Abonne();
    private int index = 0;
    private int codeRole = 0;
    private boolean isRecherche = false;
    private List<Livre> listeLivresPagination = null;
    private String recherche = null;
    private double nbTotalPages = 0;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    /**
     * permet de renvoyer sur la page accueil et de récupérer les différentes listes de livres
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model){



        //récupération du message
        String message = (String) model.getAttribute("message");

        //récupération du code role
        if(model.getAttribute("codeRole") != null) {
            codeRole = (int) model.getAttribute("codeRole");
        }
        //récupération du code role
        if(model.getAttribute("utilisateurAuthentifie") != null){
            utilisateurAuthentifie = (Abonne) model.getAttribute("utilisateurAuthentifie");
            abonnePret = livresProxy.abonnePretSelonSonId(utilisateurAuthentifie.getIdAbonne());

        }

        //récupération du top 10
        livres = livresProxy.topLivre();
        livreTop = new ArrayList<>(livres.subList(0, 10));

        //récupération des nouveautés
        ouvragesNouveaute = livresProxy.listeOuvrageNouveaute();

        //séparation en deux listes pour l'affichage
        for(Ouvrage ouvrage : ouvragesNouveaute){
            if(index < 3)
                ouvragesPremierePartie.add(ouvrage);
            else if(index < 6)
                ouvragesSecondePartie.add(ouvrage);

            index++;
        }

        //ajout dans le model
        model.addAttribute("message", message);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("livres", livreTop);
        model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
        model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);

        return "Accueil";
    }

    /**
     * permet de renvoyer sur la page détail livre et de récupérer l'id correspondant au livre
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/Detail/{id}", method = RequestMethod.GET)
    public String detailLivre(@PathVariable int id, Model model){

        //récuperation du livre en fonction de son id
        Livre livre = livresProxy.recupererUnLivre(id);
        //liste des auteurs correspondant au livre
        List<Auteur> auteurs = livre.getAuteurs();

        //ajout dans le model
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("detailLivre", livre);
        model.addAttribute("auteurs",auteurs);

        return "DetailLivre";
    }

    /**
     * permet de renvoyer sur la page du catalogue et de récupérer la liste des livres
     * @param noPage
     * @param nbLivresParPage
     * @param accesCatalogue
     * @param model
     * @return
     */
    @RequestMapping(value="/Catalogue/{noPage}/{nbLivresParPage}/{accesCatalogue}", method = RequestMethod.GET)
    public String listeLivrePagination(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable boolean accesCatalogue, Model model){
        if(isRecherche == false || accesCatalogue == true) {
            listeLivresPagination = new ArrayList<>();
            List<Livre> listeTotaleDesLivres = livresProxy.listeLivre();

            //calcule du nombre de total de pages
            nbTotalPages = (double) listeTotaleDesLivres.size() / nbLivresParPage;
            nbTotalPages = Math.ceil(nbTotalPages);

            //récupération des livres en fonction du numéro de la page
            listeLivresPagination = livresProxy.catalogueListeLivrePagination(noPage, nbLivresParPage);
            isRecherche = false;
        }else{
            if(this.recherche != null) recuperationDesLivresRecherche(noPage, nbLivresParPage);
        }

        //ajout dans le model
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);

        return "Catalogue";
    }

    /**
     * permet de renvoyer sur la page du catalogue et de récupérer la liste des livres correspondant à la recherche
     * @param model
     * @param noPage
     * @param nbLivresParPage
     * @param recherche
     * @return
     */
    @RequestMapping(value = "/Catalogue/{noPage}/{nbLivresParPage}", method = RequestMethod.POST)
    public String recherche(Model model, @PathVariable int noPage, @PathVariable int nbLivresParPage, @RequestParam String recherche){

        if(!recherche.isEmpty()) {
            //savoir que nous sommes en recherche et récupérons la recherche dans une variable de classe
            isRecherche = true;
            this.recherche = recherche;

            //nous effaçons la liste de livre du catalogue afin de la remplacer par celle de la recherche
            listeLivresPagination.clear();

            recuperationDesLivresRecherche(noPage, nbLivresParPage);
        }

        //ajout dans le model
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);

        return "Catalogue";
    }

    public void recuperationDesLivresRecherche(int noPage, int nbLivresParPage){
        //récuperation des livres en fonction du numéro de la page
        listeLivresPagination = livresProxy.catalogueListeLivrePaginationRecherche(noPage, nbLivresParPage,recherche);

        //récuperation de la liste des livres correspondant à la recherche
        List<Livre> listeLivresRecherche = livresProxy.catalogueListeLivrePaginationRecherche(recherche);

        //calcule du nombre de total de pages
        nbTotalPages = (double) listeLivresRecherche.size() / nbLivresParPage;
        nbTotalPages = Math.ceil(nbTotalPages);
    }

}
