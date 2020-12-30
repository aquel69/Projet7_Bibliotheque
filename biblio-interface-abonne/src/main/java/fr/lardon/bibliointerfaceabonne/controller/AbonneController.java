package fr.lardon.bibliointerfaceabonne.controller;

import fr.lardon.bibliointerfaceabonne.models.AuteurBean;
import fr.lardon.bibliointerfaceabonne.models.LivreBean;
import fr.lardon.bibliointerfaceabonne.models.OuvrageBean;
import fr.lardon.bibliointerfaceabonne.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AbonneController {

    @Autowired
    private MicroserviceLivresProxy livresProxy;


    @RequestMapping(value = "/")
    public String accueil(Model model){
        System.out.println("je suis dans le proxy");

        //récupération de tous les livres
        List<LivreBean> livres = livresProxy.listeLivre();
        model.addAttribute("livres", livres);

        //récupération des nouveautés
        int index = 0;

        List<OuvrageBean> ouvragesNouveaute = livresProxy.listeOuvrageNouveaute();

        List<OuvrageBean> ouvragesPremierePartie = new ArrayList<>();
        List<OuvrageBean> ouvragesSecondePartie = new ArrayList<>();

        for(OuvrageBean ouvrage : ouvragesNouveaute){
            if(index < 3)
                ouvragesPremierePartie.add(ouvrage);
            else if(index < 6)
                ouvragesSecondePartie.add(ouvrage);

            index++;
        }

        model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
        model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);


        return "Accueil";
    }

    @RequestMapping(value = "/Detail/{id}")
    public String detailLivre(@PathVariable int id, Model model){

            LivreBean livre = livresProxy.recupererUnLivre(id);

            List<AuteurBean> auteurs = livre.getAuteurs();

            model.addAttribute("detailLivre", livre);
            model.addAttribute("auteurs",auteurs);

        return "DetailLivre";
    }

    @GetMapping(value="/Catalogue/{noPage}/{nbLivresParPage}")
    public String ListeLivrePagination(@PathVariable int noPage, @PathVariable int nbLivresParPage, Model model){
        double nbTotalPages =0;

        List<LivreBean> listeLivresPagination = livresProxy.catalogueListeLivrePagination(noPage, nbLivresParPage);
        List<LivreBean> totalDesLivres = livresProxy.listeLivre();
        nbTotalPages = totalDesLivres.size() / nbLivresParPage;
        nbTotalPages = Math.floor(nbTotalPages);

        System.out.println("taille livre pagination " + totalDesLivres.size());
        System.out.println("nb livre par page " + nbLivresParPage);
        System.out.println("nb de page " + nbTotalPages);


        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);


        return "Catalogue";
    }


}
