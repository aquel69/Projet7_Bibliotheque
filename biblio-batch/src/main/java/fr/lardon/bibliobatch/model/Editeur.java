package fr.lardon.bibliobatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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
