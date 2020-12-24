package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoLivre extends JpaRepository<Livre, Integer> {

    @Query("SELECT * FROM livre WHERE id_livre BETWEEN '1' AND '8'")
    List<Livre> listeLivrePagination(int debut, int fin);
}
