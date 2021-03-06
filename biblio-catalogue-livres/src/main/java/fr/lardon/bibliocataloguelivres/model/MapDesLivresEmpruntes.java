package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;

import java.util.Map;

@Data
public class MapDesLivresEmpruntes {

    private Map<Livre, Integer> mapLivre;


}
