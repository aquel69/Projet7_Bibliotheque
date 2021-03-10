package fr.lardon.biblioauthentificationutilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
/**
 * classe permettant le démarrage du microservice authentification
 */
public class BiblioAuthentificationUtilisateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioAuthentificationUtilisateurApplication.class, args);

	}
}
