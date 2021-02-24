package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idOuvrage", scope = Ouvrage.class)*/
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
