package fr.lardon.bibliocataloguelivres.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"abonne", "pret"})
@Entity
@Table(name=("pret_abonne"))
public class ListePretAbonne {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="pret_abonne_id_pret_abonne_seq", initialValue = 2, allocationSize = 1)
    @Column(name="id_pret_abonne")
    private int idListePretAbonne;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_abonne")
    private AbonnePret abonne;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pret")
    private Pret pret;

}
