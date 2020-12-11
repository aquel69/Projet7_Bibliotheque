package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name=("auteur"))
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_auteur")
    private int idAuteur;
    /**
     * nom de l'auteur
     */
    @Column(name="nom")
    private String nom;
    /**
     * prénom de l'auteur
     */
    @Column(name="prenom")
    private String prenom;
    /**
     * date de naissance de l'auteur
     */
    @Column(name="date_de_naissance")
    private Date dateDeNaissance;
    /**
     * date de décès de l'auteur(si décédé)
     */
    @Column(name="date_deces")
    private Date dateDeces;
    /**
     * commentaire sur l'auteur
     */
    @Column(name="commentaire")
    private String commentaire;

}
