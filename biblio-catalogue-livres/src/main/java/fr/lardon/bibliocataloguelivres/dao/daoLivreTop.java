package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.LivreTop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface daoLivreTop extends JpaRepository<LivreTop, Integer> {
    @Query(value = "SELECT  l.id_livre FROM pret as pre\n" +
            "INNER JOIN ouvrage o on pre.id_ouvrage = o.id_ouvrage\n" +
            "INNER JOIN livre l on l.id_livre = o.id_livre\n" +
            "GROUP BY l.id_livre, o.nombre_exemplaires ORDER BY COUNT(l.id_livre) DESC", nativeQuery = true)
    List<LivreTop> listeNombreDePretParLivre();
}
