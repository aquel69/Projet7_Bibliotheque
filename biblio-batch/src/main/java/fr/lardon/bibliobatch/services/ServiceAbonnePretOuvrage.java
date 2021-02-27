package fr.lardon.bibliobatch.services;

import fr.lardon.bibliobatch.dao.DaoAbonne;
import fr.lardon.bibliobatch.dao.DaoPret;
import fr.lardon.bibliobatch.model.Abonne;
import fr.lardon.bibliobatch.model.AbonnePretOuvrage;

import fr.lardon.bibliobatch.model.Ouvrage;
import fr.lardon.bibliobatch.model.Pret;
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
            if(pret.isRendu() == true){
                pret.setStatut("Rendu");
                pret.setStatutPriorite("5");
            }else if(chrono > 14 && pret.isRendu() == false && pret.isProlongation() ==false){
                pret.setStatut("Prêt en cours");
                pret.setStatutPriorite("4");
            }else if(chrono > 14 && pret.isRendu() == false && pret.isProlongation() ==true){
                pret.setStatut("Prolongation");
                pret.setStatutPriorite("3");
            }else if(chrono <= 7 && chrono >= 1 && pret.isRendu() == false){
                pret.setStatut("Dernière semaine");
                pret.setStatutPriorite("2");
            }else if(chrono <= 0 && pret.isRendu() == false ){
                pret.setStatut("A rendre");
                pret.setStatutPriorite("1");
                pret.setProlongation(true);
            }
        }



        /*//mise en ordre de la liste des prêts
        for(Pret pret : listePret){
            if(pret.getStatus().equals("Prêt en cours")) {
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("Prolongation")){
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("A rendre cette semaine")){
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("A rendre bientôt")){
                listeTrie.add(listePret.get(index1));
            }else if(pret.getStatus().equals("A rendre")){
                listeTrie.add(listePret.get(index1));
            }
            index1++;
        }*/

       /* for(Pret pret : listePret){
            if(pret.getStatus().equals("Rendu")) {
                listeTrie.add(listePret.get(index2));
            }
            index2++;
        }*/

        abonnePretOuvrage.setAbonne(abonne);
        abonnePretOuvrage.setListeOuvrage(listeOuvrage);
        abonnePretOuvrage.setListePret(listePret);

        return abonnePretOuvrage;
    }

    /*public void modificationDuStatus(Pret pret){
        LocalDateTime localDateTime = LocalDateTime.now();
        long chrono = ChronoUnit.DAYS.between(localDateTime, pret.getDateDeRestitution());

        System.out.println(chrono + " seconde(s)");


    }*/

}
