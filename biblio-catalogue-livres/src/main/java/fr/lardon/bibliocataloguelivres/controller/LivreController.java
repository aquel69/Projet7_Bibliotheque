package fr.lardon.bibliocataloguelivres.controller;


import fr.lardon.bibliocataloguelivres.dao.DaoLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivreController {

    @Autowired
    private DaoLivre daoLivre;

    @RequestMapping(value = "/Livres", method = RequestMethod.GET)
    public String listeLivre(){
        return "Listes des livres";

    }

}
