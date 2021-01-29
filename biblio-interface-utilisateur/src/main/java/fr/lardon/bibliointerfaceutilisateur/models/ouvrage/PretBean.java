package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PretBean {

    private int idPret;

    /**
     * date d'emprunt
     */
    private Date dateDEmprunt;

    /**
     * date de restitution
     */
    private Date dateDeRestitution;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    private boolean prolongation;

    /**
     * ouvrage du prêt
     */
    private OuvrageBean ouvrage;

    /**
     * abonnés correspondant au prêt
     */
    private List<AbonnePretBean> abonnePrets;


}
