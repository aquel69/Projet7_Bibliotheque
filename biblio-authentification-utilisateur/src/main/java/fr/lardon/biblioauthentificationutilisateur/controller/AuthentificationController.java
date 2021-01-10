package fr.lardon.biblioauthentificationutilisateur.controller;

import fr.lardon.biblioauthentificationutilisateur.dao.DaoAbonne;
import fr.lardon.biblioauthentificationutilisateur.model.Abonne;
import fr.lardon.biblioauthentificationutilisateur.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AuthentificationController {

    @Autowired
    private DaoAbonne daoAbonne;

    BCryptPasswordEncoder bCryptPasswordEncoder;
    Abonne abonneARetourner = null;
    Role role = null;

    @GetMapping(value="/AbonnesList")
    public List<Abonne> listeAbonnes(){
        List<Abonne> listeAbonne = daoAbonne.findAll();

        return listeAbonne;
    }

    @GetMapping(value="/Login/{motDePasse}/{email}/")
    public Abonne login(@PathVariable(value ="motDePasse") String motDePasse, @PathVariable(value = "email") String email ){
        email.replaceAll("%40","@");

        System.out.println(email + motDePasse);

        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        abonneARetourner = new Abonne();
        role = new Role();



        Abonne abonne = daoAbonne.findById(20).get();


        if(bCryptPasswordEncoder.matches(motDePasse, abonne.getMotDePasse())){
            /*abonneARetourner.setRole(abonne.getRole());*/
            abonneARetourner.setPseudo(abonne.getPseudo());

        }

        return abonne;
    }

}
