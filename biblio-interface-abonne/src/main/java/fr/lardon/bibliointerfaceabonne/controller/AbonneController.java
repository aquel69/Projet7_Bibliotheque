package fr.lardon.bibliointerfaceabonne.controller;


import fr.lardon.bibliointerfaceabonne.models.LivreBean;
import fr.lardon.bibliointerfaceabonne.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AbonneController {

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    @RequestMapping(value = "/livre/{id}")
    public String accueil(@PathVariable int id, Model model){

        System.out.println("je suis dans le proxy");

        LivreBean livre = livresProxy.recupererUnProduit(id);

        model.addAttribute("livre", livre);

        return "Accueil";
    }

}

//essai
        /*Test test = new Test();
        String message = test.getMessage();
        model.addAttribute("message", message);*/


        /*List<LivreBean> livres = livresProxy.listeLivre();

        model.addAttribute("livres", livres);

        for(LivreBean livre: livres)
            System.out.println("livres : " + livre.toString());*/