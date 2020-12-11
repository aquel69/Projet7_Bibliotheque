package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name=("editeur"))
public class Editeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_editeur")
    private int idEditeur;
    /**
     * nom de la maison d'édition
     */
    @Column(name = "nom_maison_edition")
    private String nomMaisonEdition;
}
