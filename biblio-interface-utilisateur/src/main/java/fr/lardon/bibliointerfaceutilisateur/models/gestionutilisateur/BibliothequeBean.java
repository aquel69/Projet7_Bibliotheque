package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

@Data
public class BibliothequeBean {

    private String numeroSiret;

    /**
     * nom de la bibliothèque
     */
    private String nom;

    /**
     * code de la bibliothèque
     */
    private String code;

    /**
     * Role de l'abonné
     */
    private AdresseBean adresse;

}
