package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Livre;
import fr.lardon.bibliocataloguelivres.model.Pret;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DaoLivre extends JpaRepository<Livre, Integer> {

    @Query("SELECT o FROM Livre o ORDER BY titre")
    List<Livre> listeLivrePagination(Pageable pageable);

    @Query(value = "SELECT DISTINCT ON (liv.id_livre) * FROM livre as liv INNER JOIN livre_auteurs as l_a ON l_a.id_livre = liv.id_livre INNER JOIN auteur as aut ON l_a.id_auteur = aut.id_auteur WHERE liv.titre = ? OR aut.nom = ?", nativeQuery = true)
    List<Livre> listeLivreRecherchePagination(String titre, String auteur, Pageable pageable);

    @Query(value = "SELECT DISTINCT ON (liv.id_livre) * FROM livre as liv INNER JOIN livre_auteurs as l_a ON l_a.id_livre = liv.id_livre INNER JOIN auteur as aut ON l_a.id_auteur = aut.id_auteur WHERE liv.titre = ? OR aut.nom = ?", nativeQuery = true)
    List<Livre> listeLivreRecherchePagination(String titre, String auteur);

        /*@Query("SELECT l FROM Pret pre, Ouvrage o, Livre l WHERE pre.ouvragePret.idOuvrage = o.idOuvrage AND l.idLivre = o.livre.idLivre")*/
        /*@Query(value = "SELECT  l.id_livre FROM pret as pre\n" +
                "INNER JOIN ouvrage o on pre.id_ouvrage = o.id_ouvrage\n" +
                "INNER JOIN livre l on l.id_livre = o.id_livre\n" +
                "GROUP BY l.id_livre, o.nombre_exemplaires ORDER BY COUNT(l.id_livre) DESC", nativeQuery = true)
    List<Livre> listeNombreDePretParLivre();*/

}
