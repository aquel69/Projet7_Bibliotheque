package fr.lardon.bibliocataloguelivres.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"abonnePret", "ouvragePret"})
@Entity
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPret")*/
@Table(name=("pret"))
public class Pret {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="pret_id_pret_seq", initialValue = 3, allocationSize = 1)
    @Column(name="id_pret")
    private int idPret;

    /**
     * date d'emprunt
     */
    @NonNull
    @Column(name="date_emprunt")
    private LocalDateTime dateDEmprunt;

    /**
     * date de restitution
     */
    @NonNull
    @Column(name="date_restitution")
    private LocalDateTime dateDeRestitution;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    @NonNull
    @Column(name="prolongation")
    private boolean prolongation;

    /**
     * status de l'emprunt
     */
    @NonNull
    @Column(name="status")
    private String status;

    /**
     * ouvrage du prêt
     */
    @NonNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_abonne")

    private Abonne abonne;

    /**
     * ouvrage du prêt
     */
    /*@NonNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)*/
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_ouvrage")
    private Ouvrage ouvragePret;

}
