package fr.lardon.bibliocataloguelivres.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name=("pret"))
public class Pret {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="pret_id_pret_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id_pret")
    private int idPret;

    /**
     * date d'emprunt
     */
    @NonNull
    @Column(name="date_emprunt")
    @Temporal(TemporalType.DATE)
    private Date dateDEmbauche;

    /**
     * date de restitution
     */
    @NonNull
    @Column(name="date_restitution")
    @Temporal(TemporalType.DATE)
    private Date dateDepart;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    @NonNull
    @Column(name="prolongation")
    private String prolongation;

    /**
     * ouvrage du prêt
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ouvrage")

    private Ouvrage ouvrage;

    /**
     * abonnés correspondant au prêt
     */
    @ToString.Exclude
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "pret_abonne",
            joinColumns = @JoinColumn(name = "id_pret"),
            inverseJoinColumns = @JoinColumn(name = "id_abonne"))
    @JsonIgnore
    private List<AbonnePret> abonnePrets;


}
