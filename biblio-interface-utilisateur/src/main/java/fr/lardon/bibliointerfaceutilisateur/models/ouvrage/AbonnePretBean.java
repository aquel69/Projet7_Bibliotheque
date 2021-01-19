package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AbonnePretBean {

    private int idAbonne;

    /**
     * nom de l'abonné
     */
    private String nom;

    /**
     * prénom de l'abonné
     */
    private String prenom;

    /**
     * pseudo de l'abonné
     */
    private String pseudo;

    /**
     * email de l'abonné
     */
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
     * abonnés correspondant au prêt
     */
    private List<PretBean> prets;

    /**
     * Adresse de l'abonné
     */
    /*@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_adresse")

    private Adresse adresse;

    *//**
     * Role de l'abonné
     *//*
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role")

    private Role role;

    *//**
     * Bibliothèque dont l'abonné dépend
     *//*
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bibliotheque")

    private Bibliotheque bibliotheque;*/

    public AbonnePretBean() {

    }
}
