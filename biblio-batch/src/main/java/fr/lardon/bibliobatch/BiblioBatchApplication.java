package fr.lardon.bibliobatch;

import fr.lardon.bibliobatch.dao.DaoAbonnePret;
import fr.lardon.bibliobatch.dao.DaoOuvrage;
import fr.lardon.bibliobatch.dao.DaoPret;
import fr.lardon.bibliobatch.model.AbonnePret;
import fr.lardon.bibliobatch.model.Ouvrage;
import fr.lardon.bibliobatch.model.Pret;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@EnableBatchProcessing
@SpringBootApplication
public class BiblioBatchApplication implements CommandLineRunner {

	AbonnePret abonnePret;
	Ouvrage ouvrage;
	List<Pret> prets;
	Pret pret;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	DaoAbonnePret daoAbonnePret;

	@Autowired
	DaoOuvrage daoOuvrage;

	@Autowired
	DaoPret daoPret;

	public static void main(String[] args) {
		SpringApplication.run(BiblioBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws MessagingException {
		System.out.println("Sending Email...");
		abonnePret = new AbonnePret();
		ouvrage = new Ouvrage();
		prets = new ArrayList<>();

		//récupération de l'abonné
		abonnePret = daoAbonnePret.abonnePretSelonId(34);

		//récupération du prêt
		prets = daoPret.listePretSelonAbonne(34);

		//récupération de l'ouvrage
		ouvrage = daoOuvrage.findByCodeBibliotheque("AEZCON1355");



		envoiEmail(abonnePret, ouvrage, prets);

		System.out.println("Done");
	}

	public void envoiEmail(AbonnePret abonne, Ouvrage ouvrage, List<Pret> prets) throws MessagingException {

		for (Pret pret : prets){
			System.out.println("pret " + pret.toString());
		}

		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo("alexandre.lardon@yahoo.fr");

		helper.setSubject("Rappel de restitution du livre " + ouvrage.getLivre().getTitre());

		// default = text/plain
		//helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText(
				"  <body>\n" +
				"    <p>\n" +
				"      Cher, \n" + abonne.getPseudo() +
				"    </p>\n" +

				"    <p>L'ouvrage " + ouvrage.getLivre().getTitre() + " que vous avez emprunté le "  +

				"    <p>\n" +
				"      You can find <b>your inlined image</b> just below this text.\n" +
				"    </p>\n" +

				"    <p th:text=\"${abonnePret.getPseudo()}\">\n" +

				"    </p>\n" +
				"  </body>\n" +
				"</html>", true);

		javaMailSender.send(msg);

	}

}
