package fr.alardon.biblio_abonneui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.alardon")
public class BiblioAbonneuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioAbonneuiApplication.class, args);
	}

}
