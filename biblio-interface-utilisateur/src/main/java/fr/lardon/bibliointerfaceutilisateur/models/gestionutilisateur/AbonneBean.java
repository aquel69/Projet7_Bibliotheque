package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

import java.util.Date;

@Data
public class AbonneBean {

   private int idAbonne;

    /**
     * nom de l'abonné
     */
    private String nom;

    /**
     * prénom de l'abonné
     */
    private String prenom;

    /**
     * pseudo de l'abonné
     */
    private String pseudo;

    /**
     * email de l'abonné
     */
    private String email;

    /**
     * mot de passe de l'abonné
     */
    private String motDePasse;

    /**
     * numero adhérent de l'abonné
     */
    private String numeroAbonne;

    /**
     * date de création du compte
     */
    private Date dateDeCreationDuCompte;

    /**
     * Adresse de l'abonné
     */
    private AdresseBean adresse;

    /**
     * Role de l'abonné
     */
    private RoleBean role;

    /**
     * Bibliothèque dont l'abonné dépend
     */
    private BibliothequeBean bibliotheque;

}
