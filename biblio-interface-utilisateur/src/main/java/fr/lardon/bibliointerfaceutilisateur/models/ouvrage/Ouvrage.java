package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Ouvrage {

    private int idOuvrage;

    /**
     * date d'ajout de l'ouvrage'
     */

    private Date dateAjoutOuvrage;

    /**
     * code bibliothèque de l'ouvrage
     */

    private String codeBibliotheque;

    /**
     * Livre
     */

    private Livre livre;

    /**
     * liste des prêts
     */

    /*private List<Pret> listePretAbonnes;*/

}
