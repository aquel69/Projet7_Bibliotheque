package fr.lardon.biblioauthentificationutilisateur.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name=("bibliotheque"))
public class Bibliotheque {

    @Id
    @Column(name="numero_siret")
    private String numeroSiret;

    /**
     * nom de la bibliothèque
     */
    @NonNull
    @Column(name="nom")
    private String nom;

    /**
     * code de la bibliothèque
     */
    @NonNull
    @Column(name="code")
    private String code;

    /**
     * Role de l'abonné
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    public Bibliotheque() {

    }
}
