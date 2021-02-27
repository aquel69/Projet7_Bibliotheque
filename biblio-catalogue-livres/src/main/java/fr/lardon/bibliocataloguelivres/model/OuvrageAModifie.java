package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name=("ouvrage"))
public class OuvrageAModifie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ouvrage")
    private int idOuvrage;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    @Column(name = "nombre_exemplaires")
    private int nombreExemplaires;

}
