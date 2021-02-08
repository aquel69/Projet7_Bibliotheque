package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
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
