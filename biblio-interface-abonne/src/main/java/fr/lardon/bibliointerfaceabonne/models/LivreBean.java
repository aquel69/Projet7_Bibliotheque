package fr.lardon.bibliointerfaceabonne.models;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class LivreBean {

    private int idLivre;

    /**
     * Titre du livre
     */
    @NonNull
    private String titre;

    /**
     * Résumé du livre.
     */
    @NonNull
    private String resume;

    /**
     * Liste des auteurs du livre sachant qu'un livre a souvent un auteur mais peut être co-ecrit. Auquel cas, il y a plusieurs auteurs.
     */
    @ToString.Exclude
    @Setter
    private List<AuteurBean> auteurs;

    /**
     * Editeur du livre.
     */
    @NonNull
    private EditeurBean editeur;

    /**
     * Date d'édition du livre.
     */
    @NonNull
    private Date dateEdition;

    /**
     * Numéro ISBN de l'ouvrage
     */
    @NonNull
    private String numISBN13;

    /**
     * Langue de cet ouvrage.
     */
    @NonNull
    private String langue;

    /**
     * Genre associé à cet ouvrage.
     */
    @ToString.Exclude
    @Setter
    private List<GenreBean> genres;

    /**
     * nombre de pages
     */
    @NonNull
    private int nombreDePages;

    /**
     * photo de la couverture liste
     */
    @NonNull
    private String petitePhotoCouverture;

    /**
     * photo de la couverture détail
     */
    @NonNull
    private String grandePhotoCouverture;

    /**
     * photo de la couverture catalogue
     */
    @NonNull
    private String moyennePhotoCouverture;

    public LivreBean(){

    }

    public void ajouterUnGenre(GenreBean tempGenre){
        if(genres == null){
            genres = new ArrayList<>();
        }

        genres.add(tempGenre);

    }

    public void ajouterUnAuteur(AuteurBean tempAuteur){
        if(auteurs == null){
            auteurs = new ArrayList<>();
        }

        auteurs.add(tempAuteur);

    }

}
