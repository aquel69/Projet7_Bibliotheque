package fr.lardon.bibliocatalogue.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name=("genre"))
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_genre")
    private int idGenre;
    /**
     * Nom du genre littéraire.
     */
    @Column(name="nom")
    private String nom;
    /**
     * Description du genre littéraire.
     */
    @Column(name="description")
    private String description;
}
