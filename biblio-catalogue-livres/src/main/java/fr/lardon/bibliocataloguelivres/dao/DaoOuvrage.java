package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoOuvrage extends JpaRepository<Ouvrage, Integer> {

    @Query("SELECT o FROM Ouvrage o ORDER BY date_ajout DESC")
    List<Ouvrage> trouverDerniereOuvrage();

    Ouvrage findByCodeBibliotheque(String codeBibliotheque);

    @Query(value = "SELECT * FROM ouvrage WHERE id_livre = ?", nativeQuery = true)
    Ouvrage recupererUnOuvrageSelonIdLivre(int idLivre);



}
