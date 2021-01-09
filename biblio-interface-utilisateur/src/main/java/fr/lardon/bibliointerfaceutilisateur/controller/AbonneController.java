package fr.lardon.bibliointerfaceutilisateur.controller;

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
public class AbonneController {

    @Autowired
    private MicroserviceLivresProxy livresProxy;


    @RequestMapping(value = "/")
    public String accueil(Model model){
        int index = 0;
        List<LivreBean> livres;
        String message = null;

        List<OuvrageBean> ouvragesNouveaute;
        ArrayList<LivreBean> livreTop;
        List<OuvrageBean> ouvragesPremierePartie = new ArrayList<>();
        List<OuvrageBean> ouvragesSecondePartie = new ArrayList<>();

        //récupération du top 10
        livres = livresProxy.topLivre();
        livreTop = new ArrayList<>(livres.subList(0, 10));

        //récupération des nouveautés
        ouvragesNouveaute = livresProxy.listeOuvrageNouveaute();

        for(OuvrageBean ouvrage : ouvragesNouveaute){
            if(index < 3)
                ouvragesPremierePartie.add(ouvrage);
            else if(index < 6)
                ouvragesSecondePartie.add(ouvrage);

            index++;
        }

        /*model.addAttribute("message", message);*/
        model.addAttribute("message", message);
        model.addAttribute("livres", livreTop);
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

        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);

        return "Catalogue";
    }


}
