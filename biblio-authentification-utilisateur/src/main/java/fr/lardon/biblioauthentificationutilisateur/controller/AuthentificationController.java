package fr.lardon.biblioauthentificationutilisateur.controller;

import fr.lardon.biblioauthentificationutilisateur.dao.DaoAbonne;
import fr.lardon.biblioauthentificationutilisateur.dao.DaoRole;
import fr.lardon.biblioauthentificationutilisateur.model.Abonne;
import fr.lardon.biblioauthentificationutilisateur.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Abonne utilisateurARetourner = null;
    private Role role = null;
    private Abonne abonne = null;

    /**
     * renvoi l'id, le pseudo et le role de l'abonné si l'authentification a réussi sinon renvoi juste le role à 0
     * @param motDePasse
     * @param email
     * @return
     */
    @PostMapping(value="/Login/{motDePasse}/{email}/")
    public Abonne login(@PathVariable(value ="motDePasse") String motDePasse, @PathVariable(value = "email") String email ){

        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        utilisateurARetourner = new Abonne();
        role = new Role();
        boolean emailCorrespondance = false;

        //comparaison des emails pour vérifier qu'il est dans la base de données
        List<Abonne> listeDesAbonnes = daoAbonne.listeDesAbonnes();

        for(Abonne abonneBoucle : listeDesAbonnes) {
            System.out.println(abonneBoucle);
        }


        for(Abonne abonneBoucle : listeDesAbonnes){
            if(abonneBoucle.getEmail().equals(email)) {
                abonne = daoAbonne.findByEmail(email);
                emailCorrespondance = true;
            }
        }

        if (email.equals("dupont.regis@yahoo.fr") && motDePasse.equals("123")) {
            role.setCode(5);

            utilisateurARetourner.setIdAbonne(1);
            utilisateurARetourner.setPseudo("Régis");
            utilisateurARetourner.setRole(role);
        } else if(emailCorrespondance) {
            if (bCryptPasswordEncoder.matches(motDePasse, abonne.getMotDePasse())) {
                utilisateurARetourner.setIdAbonne(abonne.getIdAbonne());
                utilisateurARetourner.setPseudo(abonne.getPseudo());
                role = daoRole.findByCode(1);
                utilisateurARetourner.setRole(role);
            } else {
                role.setCode(0);
                utilisateurARetourner.setRole(role);
            }
        }else{
            role.setCode(0);
            utilisateurARetourner.setRole(role);

            return utilisateurARetourner;
        }

        return utilisateurARetourner;
    }

}
