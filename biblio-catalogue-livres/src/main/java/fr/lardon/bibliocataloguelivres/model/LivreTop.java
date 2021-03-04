package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name=("livre"))
public class LivreTop {

    @Id
    @Column(name="id_livre")
    private int idLivre;


    int nombreDePret;
}
