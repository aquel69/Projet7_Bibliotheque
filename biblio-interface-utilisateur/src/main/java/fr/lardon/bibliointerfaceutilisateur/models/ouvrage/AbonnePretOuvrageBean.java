package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;


import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AbonnePretOuvrageBean {

    private AbonneBean abonne;

    private List<PretBean> listePret;

    private List<OuvrageBean> listeOuvrage;

}
