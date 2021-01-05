package fr.lardon.bibliogestionutilisateur.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name=("enumrole"))
public class Role {

    @Id
    @Column(name="code")
    private int code;

    /**
     * status de l'utilisateur
     */
    @Column(name = "status")
    private String status;

    /**
     * description du status
     */
    @Column(name = "description")
    private String description;

}
