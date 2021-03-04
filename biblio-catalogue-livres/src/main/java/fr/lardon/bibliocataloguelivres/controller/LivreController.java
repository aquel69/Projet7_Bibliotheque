package fr.lardon.bibliocataloguelivres.controller;

import fr.lardon.bibliocataloguelivres.dao.*;
import fr.lardon.bibliocataloguelivres.model.*;
import fr.lardon.bibliocataloguelivres.services.ServiceAbonnePretOuvrage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class LivreController {

    @Autowired
    private DaoLivre daoLivre;

    @Autowired
    private DaoOuvrage daoOuvrage;

    @Autowired
    private DaoPret daoPret;

    @Autowired
    private DaoAbonnePret daoAbonnePret;

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoPretAModifie daoPretAModifie;

    @Autowired
    private DaoOuvrageAModifie daoOuvrageAModifie;

    @Autowired
    private ServiceAbonnePretOuvrage serviceAbonnePretOuvrage;

    /**
     * renvoi la liste des ouvrages en fonction de leurs nombre de fois qu'ils ont été empruntés
     * @return
     */
    @GetMapping(value = "/Top")
    public List<Ouvrage> listeOuvrageSelonNombreDEmprunt(){
        List<Ouvrage> ouvrages = daoOuvrage.listeOuvrageSelonNombreDEmprunt();

        return ouvrages;
    }

    @GetMapping(value = "/NombreDePret")
    public List<LivreTop> listeDuNombreDePretParLivre(){
        List<LivreTop> livreTops;

        livreTops = daoLivre.listeNombreDePretParLivre();

        return livreTops;
    }

    @GetMapping(value = "/ListeOuvrage")
    public List<Ouvrage> listeDesOuvrages(){
        List<Ouvrage> listeOuvrage = daoOuvrage.recupererTousLesOuvrages();

        return listeOuvrage;
    }

    /**
     * renvoi la liste de tous les livres
     * @return
     */
    @GetMapping(value = "/Livres")
    public List<Livre> listeLivre(){
        List<Livre> livres = daoLivre.findAll();

        return livres;
    }

    /**
     * renvoi la liste des livres sous forme de pagination
     * @param noPage
     * @param nbLivresParPage
     * @return
     */
    @GetMapping(value="/Catalogue/{noPage}/{nbLivresParPage}")
    public List<Livre> catalogueListeLivrePagination(@PathVariable int noPage, @PathVariable int nbLivresParPage){
        Pageable pageable = PageRequest.of(noPage, nbLivresParPage);
        List<Livre> listeLivrePagination = daoLivre.listeLivrePagination(pageable);

        return listeLivrePagination;
    }

    /**
     * renvoi la liste des livres sous forme de pagination en fonction de la recherche
     * @param noPage
     * @param nbLivresParPage
     * @param recherche
     * @return
     */
    @GetMapping(value="/Recherche/{noPage}/{nbLivresParPage}/{recherche}")
    public List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable String recherche){
        Pageable pageable = PageRequest.of(noPage, nbLivresParPage);
        List<Livre> listeLivrePagination = daoLivre.listeLivreRecherchePagination(recherche, recherche,  pageable);

        return listeLivrePagination;
    }

    /**
     * renvoi la liste des livres en fonction de la recherche
     * @param recherche
     * @return
     */
    @GetMapping(value="/Recherche/{recherche}")
    public List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable String recherche){
        List<Livre> listeLivrePagination = daoLivre.listeLivreRecherchePagination(recherche, recherche);

        return listeLivrePagination;
    }

    /**
     * récupérer un livre en fonction de son id
     * @param id
     * @return
     */
    @GetMapping( value = "/Livres/{id}")
    public Livre recupererUnLivre(@PathVariable int id) {
        Livre livre = daoLivre.findById(id).get();

        return livre;
    }

    /**
     * récupérer un ouvrage en fonction de l'id du livre
     * @param id
     * @return
     */
    @GetMapping( value = "/OuvrageSelonIdLivre/{id}")
    public Ouvrage recupererUnOuvrageSelonIdLivre(@PathVariable int id) {
        Ouvrage ouvrage = daoOuvrage.findById(id).get();

        return ouvrage;
    }

    /**
     * renvoi la liste des livres en fonction de leurs anciennetés
     * @return
     */
    @GetMapping(value = "/Livres/Nouveau")
    public List<Ouvrage> listeOuvrageNouveaute(){
        List<Ouvrage> ouvrages = daoOuvrage.trouverDerniereOuvrage();

        return ouvrages;
    }

    /**
     * ajouter un prêt dans la base de données
     * @param pret
     */
    @PostMapping(value = "/AjouterPret")
    public void ajouterPret(@RequestBody Pret pret) {
        daoPret.save(pret);
    }

    /**
     * ajouter un prêt dans la base de données
     * @param ouvrageAModifie
     */
    @PostMapping(value = "/SauvegarderOuvrage")
    public void sauvegarderOuvrage(@RequestBody OuvrageAModifie ouvrageAModifie) {
        daoOuvrageAModifie.save(ouvrageAModifie);
    }

    /**
     * renvoi l'ouvrage selon son code bibliothèque
     * @param codeBibliotheque
     * @return
     */
    @GetMapping(value = "/Ouvrage/{codeBibliotheque}")
    public Ouvrage ouvrageSelonCodeBibliotheque(@PathVariable String codeBibliotheque){
        Ouvrage ouvrage = daoOuvrage.findByCodeBibliotheque(codeBibliotheque);

        return ouvrage;
    }

    /**
     * ajouter un prêt pour l'abonné dans la base de données
     * @param pret
     */
    @PostMapping(value = "/SauvegarderPret}")
    public void sauvegarderPret(@RequestBody Pret pret) {
        daoPret.save(pret);
    }

    /**
     * ajouter un prêt pour l'abonné dans la base de données
     * @param abonnePret
     */
    @PostMapping(value = "/SauvegarderAbonne")
    public void sauvegarderAbonnePret(@RequestBody AbonnePret abonnePret) {
        daoAbonnePret.save(abonnePret);
    }

    /**
     * récupérer un abonné dans la base de données selon son numéro d'abonné
     * @param numeroAbonne
     * @return
     */
    @GetMapping(value = "/AbonnePret/{numeroAbonne}")
    public Abonne recupererAbonneSelonNumeroAbonne(@PathVariable String numeroAbonne) {
        Abonne abonne = daoAbonne.findByNumeroAbonne(numeroAbonne);

        return abonne;
    }

    @GetMapping(value = "/PretsSelonAbonne/{id}")
    public List<Pret> listeDesPretsSelonAbonne(@PathVariable int id){
        List<Pret> prets = daoPret.listePretSelonAbonne(id);

        return prets;
    }

    @GetMapping(value = "/PretSelonSonId/{id}")
    public Pret pretSelonSonId(@PathVariable int id){
        Pret pret = daoPret.findById(id).get();

        return pret;
    }

    @GetMapping(value = "/AbonnePretSelonId/{id}")
    public AbonnePretOuvrage abonnePretSelonSonId(@PathVariable int id){
        List<Pret> pretList;

        AbonnePretOuvrage abonnePret = serviceAbonnePretOuvrage.getAbonnePretOuvrage(id);
        pretList = abonnePret.getListePret();

        //sauvegarde des prêts avec leurs nouveaux statuts
        for(Pret pret : pretList){
            daoPret.save(pret);
        }

        //rappel de la liste des prêts pour la mise en ordre selon priorité
        pretList = listeDesPretsSelonAbonne(abonnePret.getAbonne().getIdAbonne());
        abonnePret.setListePret(pretList);

        return abonnePret;
    }

    @GetMapping(value = "/AbonneSelonSonId/{id}")
    public Abonne abonneSelonSonId(@PathVariable int id){
        Abonne abonne = daoAbonne.findById(id).get();

        return abonne;
    }

    @PostMapping(value = "/SauvegarderPretAModifie")
    public void sauvegardePretAModifie(@RequestBody PretAModifie pretAModifie){
        daoPretAModifie.save(pretAModifie);
    }

    @GetMapping(value = "/PretAModifieSelonSonId/{id}")
    public PretAModifie pretAModifieSelonSonId(@PathVariable int id){
        PretAModifie pretAModifie = daoPretAModifie.findById(id).get();

        return pretAModifie;
    }

    @GetMapping(value = "/OuvragesSelonIdLivre/{id}")
    public List<Ouvrage> listeDesOuvragesSelonIdLivre(@PathVariable int id){
        List<Ouvrage> ouvrages;

        ouvrages = daoOuvrage.listeOuvragesSelonIdLivre(id);

        return ouvrages;
    }
}
