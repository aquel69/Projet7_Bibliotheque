package fr.lardon.bibliocataloguelivres.services;

import fr.lardon.bibliocataloguelivres.dao.DaoAbonne;
import fr.lardon.bibliocataloguelivres.dao.DaoPret;
import fr.lardon.bibliocataloguelivres.model.*;
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
        List<Pret> listeTrie = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;

        LocalDateTime localDateTime = LocalDateTime.now();

        Abonne abonne = daoAbonne.findById(id).get();
        List<Pret> listePret = daoPret.listePretSelonAbonne(id);
        List<Ouvrage> listeOuvrage = new ArrayList<>();

        //ajout des prêt dans la liste d'ouvrage
        for(Pret pret : listePret){
            listeOuvrage.add(pret.getOuvragePret());
        }

        //changement du status suivant la période
        for(Pret pret : listePret){
            long chrono = ChronoUnit.DAYS.between(localDateTime, pret.getDateDeRestitution());
            if(chrono > 14 && pret.isRendu() == false && pret.isProlongation() ==false){
                pret.setStatus("Prêt en cours");
            }else if(chrono > 14 && pret.isRendu() == false && pret.isProlongation() ==true){
                pret.setStatus("Prolongation");
            }else if(chrono <= 14 && chrono > 7 && pret.isRendu() == false){
                pret.setStatus("A rendre bientôt");
            }else if(chrono <= 7 && chrono >= 0 && pret.isRendu() == false){
                pret.setStatus("A rendre cette semaine");
            }else if(chrono < 0 && pret.isRendu() == false ){
                pret.setStatus("A rendre");
                /*pret.setRendu(true);*/
            }
        }

        //mise en ordre de la liste des prêts
        for(Pret pret : listePret){
            if(pret.getStatus().equals("A rendre")) {
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("Prolongation")){
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("A rendre cette semaine")){
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("A rendre bientôt")){
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("Prêt en cours")){
                listeTrie.add(listePret.get(index1));
            }
            index1++;
        }

        for(Pret pret : listePret){
            if(pret.getStatus().equals("Rendu")) {
                listeTrie.add(listePret.get(index2));
            }
            index2++;
        }

        abonnePretOuvrage.setAbonne(abonne);
        abonnePretOuvrage.setListeOuvrage(listeOuvrage);
        abonnePretOuvrage.setListePret(listeTrie);

        return abonnePretOuvrage;
    }

    /*public void modificationDuStatus(Pret pret){
        LocalDateTime localDateTime = LocalDateTime.now();
        long chrono = ChronoUnit.DAYS.between(localDateTime, pret.getDateDeRestitution());

        System.out.println(chrono + " seconde(s)");


    }*/

}
