package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.AbonnePret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonnePret extends JpaRepository<AbonnePret, Integer> {

    AbonnePret findByNumeroAbonne(String numeroAbonne);

    @Query(value = "SELECT DISTINCT ON  (abo.id_abonne) * FROM abonne as abo\n" +
            "    INNER JOIN pret_abonne as pab on abo.id_abonne = pab.id_abonne\n" +
            "    INNER JOIN pret as pre on pab.id_pret = pre.id_pret\n" +
            "    INNER JOIN ouvrage as ouv on pre.id_ouvrage = ouv.id_ouvrage\n" +
            "    WHERE abo.id_abonne = ?", nativeQuery = true)
    AbonnePret abonnePretSelonId(int idAbonne);

}
