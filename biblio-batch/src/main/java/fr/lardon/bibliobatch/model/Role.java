package fr.lardon.bibliobatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
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
