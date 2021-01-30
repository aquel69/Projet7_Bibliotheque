package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListePretAbonneBean {

    private int idListePretAbonne;

    private AbonnePretBean abonne;

    private PretBean pret;

}
