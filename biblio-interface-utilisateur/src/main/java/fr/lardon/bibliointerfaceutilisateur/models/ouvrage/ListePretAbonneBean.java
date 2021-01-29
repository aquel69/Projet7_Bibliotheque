package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;

@Data
public class ListePretAbonneBean {

    private int idListePretAbonne;

    private AbonnePretBean abonne;

    private PretBean pret;

}
