package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.RoleBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AuteurBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.LivreBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.OuvrageBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogueController {
    private List<OuvrageBean> ouvragesNouveaute;
    private ArrayList<LivreBean> livreTop;
    private List<OuvrageBean> ouvragesPremierePartie = new ArrayList<>();
    private List<OuvrageBean> ouvragesSecondePartie = new ArrayList<>();
    private List<LivreBean> livres;
    private RoleBean role = new RoleBean();
    private AbonneBean utilisateurAuthentifie = new AbonneBean();
    private int index = 0;
    private int codeRole = 0;
    private boolean isRecherche = false;
    private List<LivreBean> listeLivresPagination = null;
    private String recherche = null;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model){
        //récupération du message
        String message = (String) model.getAttribute("message");

        //récupération du code role
        if(model.getAttribute("codeRole") != null) {
            codeRole = (int) model.getAttribute("codeRole");
            System.out.println("code Role " + codeRole);
        }
        //récupération du code role
        if(model.getAttribute("utilisateurAuthentifie") != null){
            utilisateurAuthentifie = (AbonneBean) model.getAttribute("utilisateurAuthentifie");
        }

        //récupération du top 10
        livres = livresProxy.topLivre();
        livreTop = new ArrayList<>(livres.subList(0, 10));

        //récupération des nouveautés
        ouvragesNouveaute = livresProxy.listeOuvrageNouveaute();

        //séparation en deux listes pour l'affichage
        for(OuvrageBean ouvrage : ouvragesNouveaute){
            if(index < 3)
                ouvragesPremierePartie.add(ouvrage);
            else if(index < 6)
                ouvragesSecondePartie.add(ouvrage);

            index++;
        }

        //ajout dans le model
        model.addAttribute("message", message);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("livres", livreTop);
        model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
        model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);

        return "Accueil";
    }

    @RequestMapping(value = "/Detail/{id}", method = RequestMethod.GET)
    public String detailLivre(@PathVariable int id, Model model){

        //récuperation du livre en fonction de son id
        LivreBean livre = livresProxy.recupererUnLivre(id);
        //liste des auteurs correspondant au livre
        List<AuteurBean> auteurs = livre.getAuteurs();

        //ajout dans le model
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("detailLivre", livre);
        model.addAttribute("auteurs",auteurs);

        return "DetailLivre";
    }

    @RequestMapping(value="/Catalogue/{noPage}/{nbLivresParPage}/{accesCatalogue}", method = RequestMethod.GET)
    public String listeLivrePagination(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable boolean accesCatalogue, Model model){
        double nbTotalPages =0;


        if(isRecherche == false || accesCatalogue == true) {
            /*if(listeLivresPagination.size() != 0) listeLivresPagination.clear();*/
            System.out.println("je suis dans la fonction");
            listeLivresPagination = new ArrayList<>();
            List<LivreBean> listeTotaleDesLivres = livresProxy.listeLivre();

            nbTotalPages = (double) listeTotaleDesLivres.size() / nbLivresParPage;
            System.out.println(nbTotalPages);
            System.out.println(listeTotaleDesLivres.size());
            nbTotalPages = Math.ceil(nbTotalPages);



            //récuperation des livres en fonction du numéro de la page
            listeLivresPagination = livresProxy.catalogueListeLivrePagination(noPage, nbLivresParPage);
            isRecherche = false;
        }else{
            if(this.recherche != null) {
                listeLivresPagination = livresProxy.catalogueListeLivrePaginationRecherche(noPage, nbLivresParPage, this.recherche);

                List<LivreBean> listeLivresRecherche = livresProxy.catalogueListeLivrePaginationRecherche(this.recherche);

                nbTotalPages = (double)listeLivresRecherche.size() / nbLivresParPage;

                nbTotalPages = Math.ceil(nbTotalPages);
            }
        }

        System.out.println("liste nb livre " + listeLivresPagination.size());
        System.out.println("nombre total de page catalogue " + nbTotalPages);

        //ajout dans le model

        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);

        return "Catalogue";
    }

    @RequestMapping(value = "/Catalogue/{noPage}/{nbLivresParPage}", method = RequestMethod.POST)
    public String emprunt(Model model, @PathVariable int noPage, @PathVariable int nbLivresParPage, @RequestParam String recherche){
        double nbTotalPages =0;

        isRecherche = true;
        this.recherche = recherche;

        listeLivresPagination.clear();

        //récuperation des livres en fonction du numéro de la page
        listeLivresPagination = livresProxy.catalogueListeLivrePaginationRecherche(noPage, nbLivresParPage,recherche);

        //récuperation de la liste des livres correspondant à la recherche
        List<LivreBean> listeLivresRecherche = livresProxy.catalogueListeLivrePaginationRecherche(recherche);

        nbTotalPages = (double) listeLivresRecherche.size() / nbLivresParPage;
        nbTotalPages = Math.ceil(nbTotalPages);



        System.out.println("nb livre" + listeLivresPagination.size());
        System.out.println("nombre total de page recherche " + nbTotalPages);

        //ajout dans le model

        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);

        return "Catalogue";
    }

}
