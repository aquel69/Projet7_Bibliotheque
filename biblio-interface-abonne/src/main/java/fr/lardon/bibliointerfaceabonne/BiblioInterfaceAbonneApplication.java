package fr.lardon.bibliointerfaceabonne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.lardon.bibliointerfaceabonne")
public class BiblioInterfaceAbonneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioInterfaceAbonneApplication.class, args);
	}

}
