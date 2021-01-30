package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenreBean {

    private int idGenre;
    /**
     * Nom du genre littéraire.
     */
    private String nom;
    /**
     * Description du genre littéraire.
     */
     private String description;
}
