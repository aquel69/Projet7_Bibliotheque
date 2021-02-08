package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OuvrageBean {

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

    private LivreBean livre;

    /**
     * liste des prêts
     */

    private List<PretBean> listePretAbonnes;

}
