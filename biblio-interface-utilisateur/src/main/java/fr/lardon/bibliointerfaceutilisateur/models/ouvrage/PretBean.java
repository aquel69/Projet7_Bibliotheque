package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PretBean {

    private int idPret;

    /**
     * date d'emprunt
     */
    private Date dateDEmbauche;

    /**
     * date de restitution
     */
    private Date dateDepart;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    private String prolongation;

    /**
     * ouvrage du prêt
     */
    private OuvrageBean ouvrage;

    /**
     * abonnés correspondant au prêt
     */
    private List<AbonnePretBean> abonnePrets;


}
