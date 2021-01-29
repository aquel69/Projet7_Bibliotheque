package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.AbonnePret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonnePret extends JpaRepository<AbonnePret, Integer> {

    AbonnePret findByNumeroAbonne(String numeroAbonne);

}
