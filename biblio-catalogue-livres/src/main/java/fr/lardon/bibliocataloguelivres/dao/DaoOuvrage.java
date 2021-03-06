package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Livre;
import fr.lardon.bibliocataloguelivres.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoOuvrage extends JpaRepository<Ouvrage, Integer> {

    @Query(value = "SELECT  DISTINCT ON(ouvrage.id_livre, ouvrage.date_ajout)* FROM Ouvrage\n" +
            "GROUP BY ouvrage.id_ouvrage, ouvrage.id_livre ORDER BY ouvrage.date_ajout DESC", nativeQuery = true)
    List<Ouvrage> trouverDerniereOuvrage();

    Ouvrage findByCodeBibliotheque(String codeBibliotheque);

    @Query(value = "SELECT * FROM ouvrage ORDER BY ouvrage.id_Livre", nativeQuery = true)
    List<Ouvrage> recupererTousLesOuvrages();

    @Query(value = "SELECT  o.id_livre, o.id_ouvrage, o.date_ajout, o.code_bibliotheque, o.code_bibliotheque, o.nombre_exemplaires, o.siret_bibliotheque FROM pret as pre\n" +
            "INNER JOIN ouvrage o on pre.id_ouvrage = o.id_ouvrage\n" +
            "GROUP BY o.id_ouvrage, pre.id_ouvrage ORDER BY count(o.id_ouvrage) DESC", nativeQuery = true)
    List<Ouvrage> listeOuvrageSelonNombreDEmprunt();

    @Query(value = "SELECT * FROM ouvrage\n" +
            "    WHERE id_livre = ? ORDER BY code_bibliotheque", nativeQuery = true)
    List<Ouvrage> listeOuvragesSelonIdLivre(int id);



}
