package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
/**
 * classe représentant un abonné
 */
public class Abonne {

   private int idAbonne;

    /**
     * nom de l'abonné
     */
    @Size(max=32, message="Entre 1 et 32 caractères")
    @NotEmpty(message = "le champ est vide")
    private String nom;

    /**
     * prénom de l'abonné
     */
    @Size(max=32, message="Entre 1 et 32 caractères")
    @NotEmpty(message = "le champ est vide")
    private String prenom;

    /**
     * pseudo de l'abonné
     */
    @Size(max=15, message="Entre 1 et 15 caractères")
    @NotEmpty(message = "le champ est vide")
    private String pseudo;

    /**
     * email de l'abonné
     */
    @Pattern(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$", message="L''adresse email est invalide")
    @NotEmpty(message = "le champ est vide")
    private String email;

    /**
     * mot de passe de l'abonné
     */
    @Min(value = 6, message = "Le mot de passe doit être composés de 6 caractères minimum")
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
    private Adresse adresse;

    /**
     * Role de l'abonné
     */
    private Role role;

    /**
     * Bibliothèque dont l'abonné dépend
     */
    private Bibliotheque bibliotheque;

}
