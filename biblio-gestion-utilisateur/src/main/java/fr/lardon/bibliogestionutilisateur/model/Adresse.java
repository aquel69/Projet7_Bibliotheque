package fr.lardon.bibliogestionutilisateur.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name=("adresse"))
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
