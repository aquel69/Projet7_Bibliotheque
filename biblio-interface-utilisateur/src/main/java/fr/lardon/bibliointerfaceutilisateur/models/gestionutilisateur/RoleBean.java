package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

@Data
public class RoleBean {

    private int code;

    /**
     * status de l'utilisateur
     */
    private String status;

    /**
     * description du status
     */
    private String description;

}
