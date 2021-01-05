package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeBean {

    private int idEmploye;

    /**
     * nom de l'employé
     */
    private String nom;

    /**
     * prénom de l'employé
     */
    private String prenom;

    /**
     * pseudo de l'employé
     */
    private String matricule;

    /**
     * date d'embauche de l'employé
     */
    private Date dateDEmbauche;

    /**
     * date de départ(fin de contrat) de l'employé
     */
    private Date dateDeDepart;

    /**
     * email de l'employé
     */
    private String email;

    /**
     * mot de passe de l'employé
     */
    private String motDePasse;

    /**
     * Adresse de l'employé
     */
    private AdresseBean adresse;

    /**
     * Bibliothèque dont l'employé dépend
     */
    private BibliothequeBean bibliotheque;

    /**
     * Role de l'employé
     */
    private RoleBean role;

}
