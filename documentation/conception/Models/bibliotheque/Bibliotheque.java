package com.lardon.projet7bibliotheque.model.bibliotheque;

import com.lardon.projet7bibliotheque.model.Adresse.Adresse;

import java.util.List;

/**
 * Information et contenu de la bibliothèque
 */
public class Bibliotheque {

    private String numeroSiret;
    private String nom;
    /**
     * Le code bibliothèque est un trigramme de lettres qui identifie de manière unique une
     * bibliothèque au sein du groupement de bibliothèques. Ce trigramme est utilisé pour les 3 premiers
     * caractères de la codification interne d'un ouvrage.
     */
    private String code;
    private Adresse adresse;


}
