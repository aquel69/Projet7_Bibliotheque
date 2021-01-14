package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.RoleBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AuteurBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.LivreBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.OuvrageBean;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogueController {
    List<OuvrageBean> ouvragesNouveaute;
    ArrayList<LivreBean> livreTop;
    List<OuvrageBean> ouvragesPremierePartie = new ArrayList<>();
    List<OuvrageBean> ouvragesSecondePartie = new ArrayList<>();
    List<LivreBean> livres;
    RoleBean role = new RoleBean();
    AbonneBean utilisateurAuthentifie = new AbonneBean();
    int index = 0;
    int codeRole = 0;

    @Autowired
    private MicroserviceLivresProxy livresProxy;



    @RequestMapping(value = "/")
    public String accueil(Model model){
        //récupération du message
        String message = (String) model.getAttribute("message");

        //récupération du code role
        if(model.getAttribute("codeRole") != null) codeRole = (int) model.getAttribute("codeRole");
        //récupération du code role
        if(model.getAttribute("utilisateurAuthentifie") != null) utilisateurAuthentifie = (AbonneBean) model.getAttribute("utilisateurAuthentifie");

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

    @RequestMapping(value = "/Detail/{id}")
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

    @GetMapping(value="/Catalogue/{noPage}/{nbLivresParPage}")
    public String listeLivrePagination(@PathVariable int noPage, @PathVariable int nbLivresParPage, Model model){
        double nbTotalPages =0;

        //récuperation des livres en fonction du numéro de la page
        List<LivreBean> listeLivresPagination = livresProxy.catalogueListeLivrePagination(noPage, nbLivresParPage);
        //calcul du nombre de page en fonction du nombre de livre
        List<LivreBean> totalDesLivres = livresProxy.listeLivre();
        nbTotalPages = totalDesLivres.size() / nbLivresParPage;
        nbTotalPages = Math.floor(nbTotalPages);

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
