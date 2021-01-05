package fr.lardon.bibliogestionutilisateur.controller;

import fr.lardon.bibliogestionutilisateur.dao.DaoAbonne;
import fr.lardon.bibliogestionutilisateur.model.Abonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionAbonneController {

    @Autowired
    private DaoAbonne daoAbonne;

    @GetMapping(value="/Abonnes")
    public List<Abonne> listeAbonnes(){
            List<Abonne> listeAbonne = daoAbonne.findAll();

        return listeAbonne;
    }

    //ajouter un produit
    @PostMapping(value = "/InscriptionAbonne")
    public void ajouterUtilisateur(@RequestBody Abonne abonne) {
        daoAbonne.save(abonne);
    }

}
