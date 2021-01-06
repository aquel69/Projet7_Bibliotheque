package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AdresseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-gestion-utilisateur", url = "localhost:9091")
public interface MicroserviceGestionUtilisateur {

    @GetMapping(value="/Abonnes")
    List<AbonneBean> listeAbonnes();

    //ajouter un abonné
    @PostMapping(value = "/InscriptionAdresse")
    void ajouterAdresse(@RequestBody AdresseBean adresseBean);

    //ajouter un abonné
    @PostMapping(value = "/InscriptionAbonne")
    void ajouterUtilisateur(@RequestBody AbonneBean abonneBean);

}
