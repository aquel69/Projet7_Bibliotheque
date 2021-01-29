package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class OuvrageBean {

    @NonNull
    private int idOuvrage;

    /**
     * date d'ajout de l'ouvrage'
     */
    @NonNull
    private Date dateAjoutOuvrage;

    /**
     * code bibliothèque de l'ouvrage
     */
    @NonNull
    private String codeBibliotheque;

    /**
     * Livre
     */
    @NonNull
    private LivreBean livre;

    /**
     * liste des prêts
     */
    @ToString.Exclude
    @Setter
    private List<PretBean> listePretAbonnes;

    public OuvrageBean(){

    }

}
