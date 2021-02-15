package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;


import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AbonnePretOuvrage {

    private Abonne abonne;

    private List<Pret> listePret;

    private List<Ouvrage> listeOuvrage;

}
