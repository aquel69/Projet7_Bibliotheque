package fr.lardon.bibliogestionutilisateur.controller;

import fr.lardon.bibliogestionutilisateur.dao.DaoAbonne;
import fr.lardon.bibliogestionutilisateur.dao.DaoAdresse;
import fr.lardon.bibliogestionutilisateur.model.Abonne;
import fr.lardon.bibliogestionutilisateur.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionAbonneController {

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoAdresse daoAdresse;

    @GetMapping(value="/Abonnes")
    public List<Abonne> listeAbonnes(){
            List<Abonne> listeAbonne = daoAbonne.findAll();

        return listeAbonne;
    }

    //ajouter un abonné
    @PostMapping(value = "/InscriptionAdresse")
    public void ajouterAdresse(@RequestBody Adresse adresse) {
        daoAdresse.save(adresse);
    }

    //ajouter un abonné
    @PostMapping(value = "/InscriptionAbonne")
    public void ajouterUtilisateur(@RequestBody Abonne abonne) {
        daoAbonne.save(abonne);
    }



}
