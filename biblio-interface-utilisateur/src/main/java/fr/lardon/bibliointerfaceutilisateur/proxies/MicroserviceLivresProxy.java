package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-livres", url = "localhost:9090")
public interface MicroserviceLivresProxy {

    @GetMapping(value = "/Livres")
    List<LivreBean> listeLivre();

    @GetMapping(value="/Catalogue/{noPage}/{nbLivresParPage}")
    List<LivreBean> catalogueListeLivrePagination(@PathVariable("noPage") int noPage, @PathVariable("nbLivresParPage") int nbLivresParPage);

    @GetMapping( value = "/Livres/{id}")
    LivreBean recupererUnLivre(@PathVariable("id") int id);

    @GetMapping(value = "/Livres/Nouveau")
    List<OuvrageBean> listeOuvrageNouveaute();

    @GetMapping(value = "/Top")
    List<LivreBean> topLivre();

    @GetMapping(value="/Recherche/{noPage}/{nbLivresParPage}/{recherche}")
    List<LivreBean> catalogueListeLivrePaginationRecherche(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable String recherche);

    @GetMapping(value="/Recherche/{recherche}")
    List<LivreBean> catalogueListeLivrePaginationRecherche( @PathVariable String recherche);

    @PostMapping(value = "/AjouterPret")
    void ajouterPret(@RequestBody PretBean pret);

    @GetMapping(value = "/Ouvrage/{codeBibliotheque}")
    OuvrageBean ouvrageSelonCodeBibliotheque(@PathVariable String codeBibliotheque);

    @PostMapping(value = "/SauvegarderPret}")
    void sauvegarderPret(@RequestBody PretBean pret);

    @GetMapping(value = "/AbonnePret/{numeroAbonne}")
    AbonnePretBean recupererAbonneSelonNumeroAbonne(@PathVariable String numeroAbonne);

    @PostMapping(value = "/SauvegarderAbonne")
    void sauvegarderAbonnePret(@RequestBody AbonnePretBean abonnePret);

    @GetMapping(value = "/PretsSelonAbonne/{id}")
    List<PretBean> listeDesPretsSelonAbonne(@PathVariable int id);

    @GetMapping(value = "/AbonnePretSelonId/{id}")
    AbonnePretOuvrageBean abonnePretSelonSonId(@PathVariable int id);


}
