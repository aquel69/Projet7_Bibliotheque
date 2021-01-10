package fr.lardon.biblioauthentificationutilisateur.dao;

import fr.lardon.biblioauthentificationutilisateur.model.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonne extends JpaRepository<Abonne, Integer> {

    Abonne findByEmail(String email);

    @Query("SELECT o FROM Abonne o WHERE o.email = ?1")
    Abonne abonneParEmail(String email);

}
