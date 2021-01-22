package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.LivreBean;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.OuvrageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


}
