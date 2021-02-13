package fr.lardon.bibliocataloguelivres.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Column(name = "code_bibliotheque")
    private String codeBibliotheque;

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
