package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Livre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DaoLivre extends JpaRepository<Livre, Integer> {

    @Query("SELECT o FROM Livre o ORDER BY id_livre")
    List<Livre> listeLivrePagination(Pageable pageable);
}
