package fr.lardon.biblioauthentificationutilisateur.controller;

import fr.lardon.biblioauthentificationutilisateur.dao.DaoAbonne;
import fr.lardon.biblioauthentificationutilisateur.dao.DaoRole;
import fr.lardon.biblioauthentificationutilisateur.model.Abonne;
import fr.lardon.biblioauthentificationutilisateur.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthentificationController {

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoRole daoRole;


    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Abonne abonneARetourner = null;
    private Role role = null;
    private Abonne abonne;
    private String emailDecoder;

    public AuthentificationController(DaoAbonne daoAbonne, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.daoAbonne = daoAbonne;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value="/Login/{motDePasse}/{email}/")
    public Abonne login(@PathVariable(value ="motDePasse") String motDePasse, @PathVariable(value = "email") String email ){

        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        abonneARetourner = new Abonne();
        role = new Role();

        abonne = daoAbonne.findByEmail(email);
        role = daoRole.findByCode(1);

        if(bCryptPasswordEncoder.matches(motDePasse, abonne.getMotDePasse())){
            abonneARetourner.setIdAbonne(abonne.getIdAbonne());
            abonneARetourner.setPseudo(abonne.getPseudo());
            abonneARetourner.setRole(role);
        }else{
            role.setCode(0);
            abonneARetourner.setRole(role);
        }

        return abonneARetourner;
    }

    @GetMapping(value="/Logout/")
    public int logout(){
        return 0;
    }

}
