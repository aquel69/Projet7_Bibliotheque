package fr.lardon.bibliointerfaceabonne.models;

import lombok.Data;


import java.util.Date;

@Data
public class OuvrageBean {

    private int idOuvrage;

    /**
     * date d'ajout de l'ouvrage'
     */
    private Date dateAjoutOuvrage;

    /**
     * code bibliothèque de l'ouvrage
     */
    private String codeBibliotheque;

    /**
     * Livre
     */
    private LivreBean livre;

}
