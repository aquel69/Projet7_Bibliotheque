package fr.lardon.bibliointerfaceutilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.lardon.bibliointerfaceutilisateur")
public class
BiblioInterfaceUtilisateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioInterfaceUtilisateurApplication.class, args);
	}

}
