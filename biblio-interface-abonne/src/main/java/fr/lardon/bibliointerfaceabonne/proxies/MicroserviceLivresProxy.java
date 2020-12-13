package fr.lardon.bibliointerfaceabonne.proxies;

import fr.lardon.bibliointerfaceabonne.models.LivreBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-livres", url = "localhost:9090")
public interface MicroserviceLivresProxy {

    @GetMapping(value = "/Livres")
    List<LivreBean> listeLivre();

    @GetMapping( value = "/Livres/{id}")
    LivreBean recupererUnProduit(@PathVariable("id") int id);

}
