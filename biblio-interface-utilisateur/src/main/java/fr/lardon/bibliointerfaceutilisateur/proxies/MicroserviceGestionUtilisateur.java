package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "microservice-gestion-utilisateur", url = "localhost:9091")
public interface MicroserviceGestionUtilisateur {

    @GetMapping(value="/Abonnes")
    List<AbonneBean> listeAbonnes();

}
