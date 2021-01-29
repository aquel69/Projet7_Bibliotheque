package fr.lardon.bibliocataloguelivres.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name=("abonne"))
public class AbonnePret {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="abonne_id_abonne_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id_abonne")
    private int idAbonne;

    /**
     * nom de l'abonné
     */
    @NonNull
    @Column(name="nom")
    private String nom;

    /**
     * prénom de l'abonné
     */
    @NonNull
    @Column(name="prenom")
    private String prenom;

    /**
     * pseudo de l'abonné
     */
    @NonNull
    @Column(name="pseudo")
    private String pseudo;

    /**
     * email de l'abonné
     */
    @NonNull
    @Column(name="email")
    private String email;

    /**
     * mot de passe de l'abonné
     */
    @NonNull
    @Column(name="mot_de_passe")
    private String motDePasse;

    /**
     * numero adhérent de l'abonné
     */
    @NonNull
    @Column(name="numero_abonne")
    private String numeroAbonne;

    /**
     * date de création du compte
     */
    @Column(name="date_creation_du_compte")
    @Temporal(TemporalType.DATE)
    private Date dateDeCreationDuCompte;

    /**
     * abonnés correspondant au prêt
     */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = ListePretAbonne.class, mappedBy = "idAbonne")
    @JsonIgnore
    private List<ListePretAbonne> listePretAbonnes;

    public AbonnePret() {

    }
}
