package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"livre"})
@Entity
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
     * nombre d'exemplaire de l'ouvrage
     */
    @NonNull
    @Column(name = "siret_bibliotheque")
    private String siretBibliotheque;

    /**
     * Livre
     */
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_livre")
    private Livre livre;



}
