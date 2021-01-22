package fr.lardon.bibliointerfaceutilisateur.proxies;


import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "microservice-authentification-utilisateur", url = "localhost:9092")
public interface MicroserviceAuthentificationUtilisateur {

    @PostMapping(value="/Login/{motDePasse}/{email}/")
    AbonneBean login(@PathVariable(value ="motDePasse") String motDePasse, @PathVariable(value = "email") String email );

}
