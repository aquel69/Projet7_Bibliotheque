package fr.lardon.bibliocataloguelivres.controller;

import fr.lardon.bibliocataloguelivres.dao.DaoLivre;
import fr.lardon.bibliocataloguelivres.dao.DaoOuvrage;
import fr.lardon.bibliocataloguelivres.model.Livre;
import fr.lardon.bibliocataloguelivres.model.Ouvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivreController {

    @Autowired
    private DaoLivre daoLivre;

    @Autowired
    private DaoOuvrage daoOuvrage;

    @GetMapping(value = "/Livres")
    public List<Livre> listeLivre(){
        System.out.println("démarrage de la fonction listeLivre");

        List<Livre> livres = daoLivre.findAll();

        return livres;
    }

    @GetMapping( value = "/Livres/{id}")
    public Livre recupererUnLivre(@PathVariable int id) {

        /*Livre livre = daoLivre.getOne(id);*/
        Livre livre = daoLivre.findById(id).get();

        return livre;
    }

    @GetMapping(value = "/Livres/Nouveau")
    public List<Ouvrage> listeOuvrageNouveaute(){

        List<Ouvrage> ouvrages = daoOuvrage.trouverDerniereOuvrage();

        return ouvrages;
    }

}
