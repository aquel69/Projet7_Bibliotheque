package fr.lardon.bibliocataloguelivres.controller;

import fr.lardon.bibliocataloguelivres.dao.DaoLivre;
import fr.lardon.bibliocataloguelivres.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivreController {

    @Autowired
    private DaoLivre daoLivre;

    @GetMapping(value = "/Livres")
    public List<Livre> listeLivre(){
        System.out.println("démarrage de la fonction listeLivre");

        List<Livre> livres = daoLivre.findAll();

        for(Livre livre: livres)
            System.out.println("livres : " + livre.toString());

        return livres;
    }

    @GetMapping( value = "/Livres/{id}")
    public Livre recupererUnProduit(@PathVariable int id) {

        Livre livre = daoLivre.getOne(id);

        return livre;
    }

}
