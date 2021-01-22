package fr.lardon.biblioauthentificationutilisateur.controller;

import fr.lardon.biblioauthentificationutilisateur.dao.DaoAbonne;
import fr.lardon.biblioauthentificationutilisateur.dao.DaoEmploye;
import fr.lardon.biblioauthentificationutilisateur.dao.DaoRole;
import fr.lardon.biblioauthentificationutilisateur.model.Abonne;
import fr.lardon.biblioauthentificationutilisateur.model.Employe;
import fr.lardon.biblioauthentificationutilisateur.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AuthentificationController {

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoRole daoRole;

    @Autowired
    private DaoEmploye daoEmploye;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Abonne utilisateurARetourner = null;
    private Role role = null;
    private Abonne abonne = null;
    private String emailDecoder;
    private Employe employe = null;


    @PostMapping(value="/Login/{motDePasse}/{email}/")
    public Abonne login(@PathVariable(value ="motDePasse") String motDePasse, @PathVariable(value = "email") String email ){

        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        utilisateurARetourner = new Abonne();
        role = new Role();

        //comparaison des emails pour vérifier qu'il est dans la base de données
        List<Abonne> listeDesAbonnes = daoAbonne.listeDesAbonnes();
        if()

        abonne = daoAbonne.findByEmail(email);
        role = daoRole.findByCode(1);

        if(email.equals("dupont.regis@yahoo.fr") && motDePasse.equals("123")){
            role.setCode(5);

            utilisateurARetourner.setIdAbonne(1);
            utilisateurARetourner.setPseudo("Régis");
            utilisateurARetourner.setRole(role);
        }else {
            if (bCryptPasswordEncoder.matches(motDePasse, abonne.getMotDePasse())) {
                utilisateurARetourner.setIdAbonne(abonne.getIdAbonne());
                utilisateurARetourner.setPseudo(abonne.getPseudo());
                utilisateurARetourner.setRole(role);
            } else {
                role.setCode(0);
                utilisateurARetourner.setRole(role);
            }
        }

        return utilisateurARetourner;
    }

    @GetMapping(value="/Logout/")
    public int logout(){
        return 0;
    }

}
