package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    /**
     * date de décès de l'auteur(si décédé)
     */
    @Column(name="date_deces")
    @Temporal(TemporalType.DATE)
    private Date dateDeces;
    /**
     * commentaire sur l'auteur
     */
    @Column(name="commentaire")
    private String commentaire;
    /**
     * liste des livres en fonction de l'auteur
     */
    @ToString.Exclude
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "livre_auteur",
            joinColumns = @JoinColumn(name = "id_auteur"),
            inverseJoinColumns = @JoinColumn(name = "id_livre"))
    private List<Livre> livre;

}
