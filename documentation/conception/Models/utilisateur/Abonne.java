package com.lardon.projet7bibliotheque.model.utilisateur;

import com.lardon.projet7bibliotheque.model.Adresse.Adresse;
import com.lardon.projet7bibliotheque.model.bibliotheque.Bibliotheque;
import com.lardon.projet7bibliotheque.model.bibliotheque.Pret;
import com.lardon.projet7bibliotheque.model.referentiel.EnumRole;

import java.util.Date;
import java.util.List;

public class Abonne {
    private int idAbonne;
    private String nom;
    private String prenom;
    private String pseudo;
    private Adresse adresse;
    private String email;
    private String motDePasse;
    private String numeroAbonne;
    private Date dateCreationDuCompte;
    private EnumRole role;
    private List<Pret> listeDePret;
    private Bibliotheque bibliotheque;

}
