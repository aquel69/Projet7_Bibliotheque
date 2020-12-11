package fr.lardon.bibliocataloguelivres.dao;



import fr.lardon.bibliocataloguelivres.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoLivre extends JpaRepository<Livre, Integer> {

}
