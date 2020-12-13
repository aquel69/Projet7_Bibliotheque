package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    /**
     * Liste des livres en fonction du genre
     */
    @ToString.Exclude
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "liste_genre",
            joinColumns = @JoinColumn(name = "id_genre"),
            inverseJoinColumns = @JoinColumn(name = "id_livre"))
    private List<Livre> livres;
}
