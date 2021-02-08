package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PretBean {

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
     * abonné du prêt
     */
    private AbonnePretBean abonnePret;

    /**
     * ouvrage du prêt
     */
    private OuvrageBean ouvragePret;

}
