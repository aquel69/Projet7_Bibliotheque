package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class AbonneModifieBean {

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
