package fr.lardon.bibliointerfaceabonne.models;

import lombok.Data;

import java.util.Date;

@Data
public class AuteurBean {


    private int idAuteur;
    /**
     * nom de l'auteur
     */
    private String nom;
    /**
     * prénom de l'auteur
     */
     private String prenom;
    /**
     * date de naissance de l'auteur
     */
     private Date dateDeNaissance;
    /**
     * date de décès de l'auteur(si décédé)
     */
     private Date dateDeces;
    /**
     * commentaire sur l'auteur
     */
     private String commentaire;

}
