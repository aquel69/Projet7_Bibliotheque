package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Pret {

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
     * status de l'emprunt
     */
    private String status;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    private boolean prolongation;

    /**
     * abonné du prêt
     */
    private Abonne abonne;

    /**
     * ouvrage du prêt
     */
    private Ouvrage ouvragePret;

}
