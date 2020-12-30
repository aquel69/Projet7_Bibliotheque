package fr.lardon.bibliointerfaceabonne.proxies;

import fr.lardon.bibliointerfaceabonne.models.LivreBean;
import fr.lardon.bibliointerfaceabonne.models.OuvrageBean;
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


}
