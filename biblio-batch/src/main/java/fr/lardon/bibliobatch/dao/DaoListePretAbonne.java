package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.ListePretAbonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoListePretAbonne extends JpaRepository<ListePretAbonne, Integer> {
}
