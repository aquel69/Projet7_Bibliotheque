package fr.lardon.bibliointerfaceabonne.models;

import lombok.Data;

@Data
public class GenreBean {

    private int idGenre;
    /**
     * Nom du genre littéraire.
     */
    private String nom;
    /**
     * Description du genre littéraire.
     */
     private String description;
}
