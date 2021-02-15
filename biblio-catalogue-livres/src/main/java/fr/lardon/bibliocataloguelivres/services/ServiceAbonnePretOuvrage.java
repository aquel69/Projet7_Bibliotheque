package fr.lardon.bibliocataloguelivres.services;

import fr.lardon.bibliocataloguelivres.dao.DaoAbonne;
import fr.lardon.bibliocataloguelivres.dao.DaoPret;
import fr.lardon.bibliocataloguelivres.model.Abonne;
import fr.lardon.bibliocataloguelivres.model.AbonnePretOuvrage;
import fr.lardon.bibliocataloguelivres.model.Ouvrage;
import fr.lardon.bibliocataloguelivres.model.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAbonnePretOuvrage {

    @Autowired
    private DaoPret daoPret;

    @Autowired
    private DaoAbonne daoAbonne;

    public AbonnePretOuvrage getAbonnePretOuvrage(int id){
        AbonnePretOuvrage abonnePretOuvrage = new AbonnePretOuvrage();
        Abonne abonne = daoAbonne.findById(id).get();
        List<Pret> listePret = daoPret.listePretSelonAbonne(id);
        List<Ouvrage> listeOuvrage = new ArrayList<>();

        for(Pret pret : listePret){
            listeOuvrage.add(pret.getOuvragePret());
            modificationDuStatus(pret);
        }

        abonnePretOuvrage.setAbonne(abonne);
        abonnePretOuvrage.setListeOuvrage(listeOuvrage);
        abonnePretOuvrage.setListePret(listePret);

        return abonnePretOuvrage;
    }

    public void modificationDuStatus(Pret pret){
        LocalDateTime localDateTime = LocalDateTime.now();
        long chrono = ChronoUnit.DAYS.between(localDateTime, pret.getDateDeRestitution());

        System.out.println(chrono + " seconde(s)");


    }

}
