package fr.lardon.bibliogestionutilisateur.controller;

import fr.lardon.bibliogestionutilisateur.dao.DaoAbonne;
import fr.lardon.bibliogestionutilisateur.dao.DaoAdresse;
import fr.lardon.bibliogestionutilisateur.dao.DaoBibliotheque;
import fr.lardon.bibliogestionutilisateur.dao.DaoRole;
import fr.lardon.bibliogestionutilisateur.model.Abonne;
import fr.lardon.bibliogestionutilisateur.model.Adresse;
import fr.lardon.bibliogestionutilisateur.model.Bibliotheque;
import fr.lardon.bibliogestionutilisateur.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GestionAbonneController {

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoAdresse daoAdresse;

    @Autowired
    private DaoRole daoRole;

    @Autowired
    private DaoBibliotheque daoBibliotheque;

    @GetMapping(value="/Abonnes")
    public List<Abonne> listeAbonnes(){
            List<Abonne> listeAbonne = daoAbonne.findAll();

        return listeAbonne;
    }

    //modifier un abonné
    @PutMapping(value="/ModifierAbonne")
    public void modifierAbonne(@RequestBody Abonne abonne) {daoAbonne.save(abonne);}

    //ajouter un abonné
    @PostMapping(value = "/AjouterAdresse")
    public void ajouterAdresse(@RequestBody Adresse adresse) {
        daoAdresse.save(adresse);
    }

    //ajouter un abonné
    @PostMapping(value = "/AjouterAbonne")
    public void ajouterAbonne(@RequestBody Abonne abonne) {daoAbonne.save(abonne);}

    @GetMapping(value = "/Role/{id}")
    public Role recupererRole(@PathVariable int id) {

        Role role = daoRole.findById(id).get();

        return role;
    }

    @GetMapping(value = "/Bibliotheque/{siret}")
    public Bibliotheque recupererBibliotheque(@PathVariable String siret) {

        /*Livre livre = daoLivre.getOne(id);*/
        Bibliotheque bibliotheque = daoBibliotheque.findByNumeroSiret(siret);

        return bibliotheque;
    }

    @GetMapping(value = "/Adresse/{id}")
    public Adresse recupererAdresse(@PathVariable int id) {

        /*Livre livre = daoLivre.getOne(id);*/
        Adresse adresse = daoAdresse.findById(id).get();

        return adresse;
    }

    @GetMapping(value = "/Abonne/{id}")
    public Abonne recupererAbonne(@PathVariable int id) {

        Abonne abonne = daoAbonne.findById(id).get();

        return abonne;
    }

    @GetMapping(value = "/DerniereAdresse")
    public Adresse recupererDernierAdresse() {
        int dernierIdAdresse = daoAdresse.recupererDernierAdresse();

        Adresse adresse = recupererAdresse(dernierIdAdresse);

        return adresse;
    }

    @GetMapping(value = "/DerniereAbonne")
    public Abonne recupererDernierAbonne() {
        int dernierIdAbonne = daoAbonne.recupererDernierAbonne();

        Abonne abonne = recupererAbonne(dernierIdAbonne);

        return abonne;
    }

    @GetMapping(value = "/DoublonEmail")
    public Boolean verificationSiEmailDoublon(Abonne abonne){
        boolean resultat = false;
        List<Abonne> abonnes = new ArrayList<>();

        abonnes = daoAbonne.findAll();

        for (Abonne abonne1 : abonnes){
            if(abonne1.getEmail().equals(abonne.getEmail())){
                resultat = true;
            }
        }

        return resultat;
    }



}
