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

    @Query(value = "SELECT * FROM livre as a WHERE a.titre LIKE concat('%', ?, '%')", nativeQuery = true)
    List<Livre> listeLivreRecherchePagination(String titre, Pageable pageable);

    @Query(value = "SELECT * FROM livre as a WHERE a.titre LIKE concat('%', ?, '%')", nativeQuery = true)
    List<Livre> listeLivreRecherchePagination(String titre);

    @Query("SELECT o FROM Livre o")
    List<Livre> listeLivreTop();

    /*@Query("SELECT t FROM Thing t WHERE t.fooIn = ?1 AND t.bar = ?2")
    ThingEntity findByFooInAndBar(String fooIn, String bar);*/
}
