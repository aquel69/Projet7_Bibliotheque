package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OuvrageAModifie {

    private int idOuvrage;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    private int nombreExemplaires;

}
