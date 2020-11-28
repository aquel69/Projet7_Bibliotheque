package com.lardon.projet7bibliotheque.model.bibliotheque;

import com.lardon.projet7bibliotheque.model.livre.Livre;

/**
 * Réference et titre de l'ouvrage
 */
public class Ouvrage {

    private int id;
    /**
     * livre
     */
    private Livre livre;
    /**
     * Code Interne Bibliothèque rattachée à cet ouvrage.
     */
    private String codeBibliotheque;

    private Bibliotheque bibliotheque;

}
