package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"livre", "listePretAbonnes"})
@Entity
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idOuvrage", scope = Ouvrage.class)*/
@Table(name=("ouvrage"))
public class Ouvrage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ouvrage")
    private int idOuvrage;

    /**
     * date d'ajout de l'ouvrage'
     */
    @NonNull
    @Column(name="date_ajout")
    @Temporal(TemporalType.DATE)
    private Date dateAjoutOuvrage;

    /**
     * code bibliothèque de l'ouvrage
     */
    @NonNull
    @Column(name = "code_bibliotheque")
    private String codeBibliotheque;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    @NonNull
    @Column(name = "nombre_exemplaires")
    private int nombreExemplaires;

    /**
     * Livre
     */
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_livre")
    private Livre livre;

    /**
     * liste des prêts
     */
    /*@NonNull
    @JsonManagedReference
   *//* @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="idPret")*//*
    @OneToMany(mappedBy = "ouvragePret",  fetch = FetchType.EAGER)
    private List<Pret> listePretAbonnes;*/

}
