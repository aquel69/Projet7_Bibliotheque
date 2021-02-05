package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AbonnePretBean {

    private int idAbonne;

    /**
     * nom de l'abonné
     */
    @NonNull
    private String nom;

    /**
     * prénom de l'abonné
     */
    @NonNull
    private String prenom;

    /**
     * pseudo de l'abonné
     */
    @NonNull
    private String pseudo;

    /**
     * email de l'abonné
     */
    @NonNull
    private String email;

    /**
     * mot de passe de l'abonné
     */
    @NonNull
    private String motDePasse;

    /**
     * numero adhérent de l'abonné
     */
    @NonNull
    private String numeroAbonne;

    /**
     * date de création du compte
     */
    @NonNull
    private Date dateDeCreationDuCompte;

    /**
     * liste des prêts de l'abonné
     */
    @ToString.Exclude
    @Setter
    private List<PretBean> listePretAbonnes;

}
