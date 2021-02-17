package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PretAModifie {


    private int idPret;

    /**
     * date d'emprunt
     */
    private LocalDateTime dateDEmprunt;

    /**
     * date de restitution
     */
    private LocalDateTime dateDeRestitution;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    private boolean prolongation;

    /**
     * l'abonné a rendu l'ouvrage
     */
    private boolean rendu;

    /**
     * status de l'emprunt
     */
    private String status;

}
