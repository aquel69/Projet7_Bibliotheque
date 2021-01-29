package fr.lardon.bibliocataloguelivres.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
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
     * Livre
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_livre")
    private Livre livre;

    /**
     * liste des prêts
     */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Pret.class, mappedBy = "ouvrage")
    @JsonIgnore
    private List<Pret> listePretAbonnes;

    public Ouvrage(){

    }

}
