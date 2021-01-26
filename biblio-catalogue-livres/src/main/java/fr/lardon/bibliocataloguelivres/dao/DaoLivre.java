package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Livre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DaoLivre extends JpaRepository<Livre, Integer> {

    @Query("SELECT o FROM Livre o ORDER BY id_livre")
    List<Livre> listeLivrePagination(Pageable pageable);

    @Query(value = "SELECT DISTINCT ON (liv.id_livre) * FROM livre as liv INNER JOIN livre_auteurs as l_a ON l_a.id_livre = liv.id_livre INNER JOIN auteur as aut ON l_a.id_auteur = aut.id_auteur WHERE liv.titre = ? OR aut.nom = ?", nativeQuery = true)
    List<Livre> listeLivreRecherchePagination(String titre, String auteur, Pageable pageable);

    @Query(value = "SELECT DISTINCT ON (liv.id_livre) * FROM livre as liv INNER JOIN livre_auteurs as l_a ON l_a.id_livre = liv.id_livre INNER JOIN auteur as aut ON l_a.id_auteur = aut.id_auteur WHERE liv.titre = ? OR aut.nom = ?", nativeQuery = true)
    List<Livre> listeLivreRecherchePagination(String titre, String auteur);


    @Query("SELECT o FROM Livre o")
    List<Livre> listeLivreTop();

    /*@Query("SELECT t FROM Thing t WHERE t.fooIn = ?1 AND t.bar = ?2")
    ThingEntity findByFooInAndBar(String fooIn, String bar);*/
}
