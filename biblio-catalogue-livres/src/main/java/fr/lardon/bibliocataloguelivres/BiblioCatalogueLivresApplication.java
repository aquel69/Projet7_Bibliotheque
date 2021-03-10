package fr.lardon.bibliocataloguelivres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * classe permettant le démarrage du microservice catalogue livres
 */
public class BiblioCatalogueLivresApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioCatalogueLivresApplication.class, args);
	}

}
