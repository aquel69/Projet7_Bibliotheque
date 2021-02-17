package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name=("pret"))
public class PretAModifie {

    @Id
    @Column(name="id_pret")
    private int idPret;

    /**
     * date d'emprunt
     */
    @Column(name="date_emprunt")
    private LocalDateTime dateDEmprunt;

    /**
     * date de restitution
     */
    @Column(name="date_restitution")
    private LocalDateTime dateDeRestitution;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    @Column(name="prolongation")
    private boolean prolongation;

    /**
     * l'abonné a rendu l'ouvrage
     */
    @Column(name="rendu")
    private boolean rendu;

    /**
     * status de l'emprunt
     */
    @Column(name="status")
    private String status;

}
