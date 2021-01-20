package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AdresseBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.BibliothequeBean;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.RoleBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-gestion-utilisateur", url = "localhost:9091")
public interface MicroserviceGestionUtilisateur {

    @GetMapping(value="/Abonnes")
    List<AbonneBean> listeAbonnes();

    //récuperer abonné en fonction de son id
    @GetMapping(value = "/Abonne/{id}")
    public AbonneBean recupererAbonne(@PathVariable int id);

    @PutMapping(value="/ModifierAbonne")
    public void modifierAbonne(@RequestBody AbonneBean abonne);

    //ajouter une adresse
    @PostMapping(value = "/AjouterAdresse")
    void ajouterAdresse(@RequestBody AdresseBean adresseBean);

    //ajouter un abonné
    @PostMapping(value = "/AjouterAbonne")
    void ajouterAbonne(@RequestBody AbonneBean abonneBean);

    //récuperer la bibliothèque de l'abonné
    @GetMapping(value = "/Bibliotheque/{siret}")
    BibliothequeBean recupererBibliotheque(@PathVariable String siret);

    //récuperer le role de l'abonné
    @GetMapping(value = "/Role/{id}")
    RoleBean recupererRole(@PathVariable int id);

    //récuperer l'adresse par l'id
    @GetMapping(value = "/Adresse/{id}")
    AdresseBean recupererAdresse(@PathVariable int id);

    //récuperer la derniere adresse de la base de données
    @GetMapping(value = "/DerniereAdresse")
    AdresseBean recupererDernierAdresse();

    //récuperer le dernier abonné de la base de données
    @GetMapping(value = "/DerniereAbonne")
    AbonneBean recupererDernierAbonne();

    //vérification si l'email n'existe pas
    @GetMapping(value = "/DoublonEmail")
    Boolean verificationSiEmailDoublon(AbonneBean abonne);

}
