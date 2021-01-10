package fr.lardon.biblioauthentificationutilisateur.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="adresse", schema = "public")
public class Adresse {

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE)*/
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="adresse_id_seq", initialValue = 5, allocationSize = 1)
    @Column(name="id_adresse")
    private int idAdresse;

    /**
     * numéro de l'adresse
     */
    @Column(name = "numero")
    private String numero;

    /**
     * rue de l'adresse
     */
    @Column(name = "rue")
    private String rue;

    /**
     * complément de l'adresse
     */
    @Column(name = "complement")
    private String complement;

    /**
     * code postal de l'adresse
     */
    @Column(name = "code_postal")
    private String codePostal;

    /**
     * ville de l'adresse
     */
    @Column(name = "ville")
    private String ville;

}
