package fr.lardon.bibliobatch.controller;

import fr.lardon.bibliobatch.dao.DaoPret;
import fr.lardon.bibliobatch.model.AbonnePretOuvrage;
import fr.lardon.bibliobatch.model.Pret;
import fr.lardon.bibliobatch.services.ServiceAbonnePretOuvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatchController {

    @Autowired
    private DaoPret daoPret;

    @Autowired
    private ServiceAbonnePretOuvrage serviceAbonnePretOuvrage;

    @GetMapping(value = "/AbonnePretSelonId/{id}")
    public AbonnePretOuvrage abonnePretSelonSonId(@PathVariable int id){
        List<Pret> pretList;

        AbonnePretOuvrage abonnePret = serviceAbonnePretOuvrage.getAbonnePretOuvrage(id);
        pretList = abonnePret.getListePret();

        //sauvegarde des prêts avec leurs nouveaux statuts
        for(Pret pret : pretList){
            daoPret.save(pret);
        }

        //rappel de la liste des prêts pour la mise en ordre selon priorité
        abonnePret.setListePret(pretList);

        return abonnePret;
    }
}
