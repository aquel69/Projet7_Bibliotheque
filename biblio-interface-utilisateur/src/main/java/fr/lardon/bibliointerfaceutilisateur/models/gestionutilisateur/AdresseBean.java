package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

@Data
public class AdresseBean {

    private int idAdresse;

    /**
     * numéro de l'adresse
     */
    private String numero;

    /**
     * rue de l'adresse
     */
    private String rue;

    /**
     * complément de l'adresse
     */
    private String complement;

    /**
     * code postal de l'adresse
     */
    private String codePostal;

    /**
     * ville de l'adresse
     */
    private String ville;

}
