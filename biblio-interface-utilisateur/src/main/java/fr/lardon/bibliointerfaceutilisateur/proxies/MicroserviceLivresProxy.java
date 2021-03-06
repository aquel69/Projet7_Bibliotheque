package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-livres", url = "localhost:9090")
public interface MicroserviceLivresProxy {

    @GetMapping(value = "/Livres")
    List<Livre> listeLivre();

    @GetMapping(value = "/ListeOuvrage")
    List<Ouvrage> listeDesOuvrages();

    @GetMapping(value="/Catalogue/{noPage}/{nbLivresParPage}")
    List<Livre> catalogueListeLivrePagination(@PathVariable("noPage") int noPage, @PathVariable("nbLivresParPage") int nbLivresParPage);

    @GetMapping( value = "/Livres/{id}")
    Livre recupererUnLivre(@PathVariable("id") int id);

    @GetMapping(value = "/Livres/Nouveau")
    List<Ouvrage> listeOuvrageNouveaute();

    @GetMapping(value = "/Top")
    List<Livre> listeOuvrageSelonNombreDEmprunt();

    @GetMapping(value="/Recherche/{noPage}/{nbLivresParPage}/{recherche}")
    List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable String recherche);

    @GetMapping(value="/Recherche/{recherche}")
    List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable String recherche);

    @PostMapping(value = "/AjouterPret")
    void ajouterPret(@RequestBody Pret pret);

    @GetMapping(value = "/Ouvrage/{codeBibliotheque}")
    Ouvrage ouvrageSelonCodeBibliotheque(@PathVariable String codeBibliotheque);

    @PostMapping(value = "/SauvegarderPret}")
    void sauvegarderPret(@RequestBody Pret pret);

    @GetMapping(value = "/AbonnePret/{numeroAbonne}")
    Abonne recupererAbonneSelonNumeroAbonne(@PathVariable String numeroAbonne);

    @PostMapping(value = "/SauvegarderAbonne")
    void sauvegarderAbonnePret(@RequestBody AbonnePret abonnePret);

    @GetMapping(value = "/PretsSelonAbonne/{id}")
    List<Pret> listeDesPretsSelonAbonne(@PathVariable int id);

    @GetMapping(value = "/AbonnePretSelonId/{id}")
    AbonnePretOuvrage abonnePretSelonSonId(@PathVariable int id);

    @GetMapping(value = "/PretSelonSonId/{id}")
    Pret pretSelonSonId(@PathVariable int id);

    @PostMapping(value = "/SauvegarderPretAModifie")
    void sauvegardePretAModifie(@RequestBody PretAModifie pretAModifie);

    @GetMapping(value = "/PretAModifieSelonSonId/{id}")
    PretAModifie pretAModifieSelonSonId(@PathVariable int id);

    @GetMapping( value = "/OuvrageSelonIdLivre/{id}")
    Ouvrage recupererUnOuvrageSelonIdLivre(@PathVariable int id);

    @PostMapping(value = "/SauvegarderOuvrage")
    void sauvegarderOuvrage(@RequestBody OuvrageAModifie ouvrageAModifie);

    @GetMapping(value = "/OuvragesSelonIdLivre/{id}")
    List<Ouvrage> listeDesOuvragesSelonIdLivre(@PathVariable int id);


}
