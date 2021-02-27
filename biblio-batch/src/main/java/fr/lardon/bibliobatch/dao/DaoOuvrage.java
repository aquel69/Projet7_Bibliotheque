package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.Ouvrage;
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

    @Query(value = "SELECT  o.id_livre, o.id_ouvrage, o.date_ajout, o.code_bibliotheque, o.code_bibliotheque, o.nombre_exemplaires FROM pret as pre\n" +
            "INNER JOIN ouvrage o on pre.id_ouvrage = o.id_ouvrage\n" +
            "GROUP BY o.id_ouvrage, pre.id_ouvrage ORDER BY count(o.id_ouvrage) DESC", nativeQuery = true)
    List<Ouvrage> listeOuvrageSelonNombreDEmprunt();



}
