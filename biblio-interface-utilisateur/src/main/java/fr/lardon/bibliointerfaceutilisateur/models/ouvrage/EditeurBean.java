package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditeurBean {

    private int idEditeur;

    /**
     * nom de la maison d'édition
     */
     private String nomMaisonEdition;
}
