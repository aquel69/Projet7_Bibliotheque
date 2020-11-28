package com.lardon.projet7bibliotheque.model.livre;

import com.lardon.projet7bibliotheque.model.referentiel.Genre;

import java.util.List;

public class Livre {

    private int idLivre;
    /**
     * Titre du livre
     */
    private String titre;
    /**
     * Résumé du livre.
     */
    private String resume;
    /**
     * Liste des auteurs du livre sachant qu'un livre a souven un auteur mais peut être co-ecrit. Auquel cas, il y a plusieurs auteurs.
     */
    private List<Auteur> auteurs;
    /**
     * Editeur du livre.
     */
    private Editeur editeur;
    /**
     * Date d'édition du livre.
     */
    private String dateEdition;
    /**
     * Numéro ISBN de l'ouvrage
     */
    private String numISBN13;
    /**
     * Langue de cet ouvrage.
     */
    private String langue;
    /**
     * Genre associé à cet ouvrage.
     */
    private List<Genre> genres;
    /**
     * nombre de pages
     */
    private int nompbreDePages;

}
