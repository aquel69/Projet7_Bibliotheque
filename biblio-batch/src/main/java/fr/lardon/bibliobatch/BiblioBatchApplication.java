package fr.lardon.bibliobatch;

import fr.lardon.bibliobatch.dao.DaoAbonnePret;
import fr.lardon.bibliobatch.dao.DaoOuvrage;
import fr.lardon.bibliobatch.dao.DaoPret;
import fr.lardon.bibliobatch.model.AbonnePret;
import fr.lardon.bibliobatch.model.Mail;
import fr.lardon.bibliobatch.model.Ouvrage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@EnableBatchProcessing
@SpringBootApplication
public class BiblioBatchApplication implements CommandLineRunner {

	AbonnePret abonnePret;
	Ouvrage ouvrage;
	Mail mail;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	DaoAbonnePret daoAbonnePret;

	@Autowired
	DaoOuvrage daoOuvrage;

	@Autowired
	DaoPret daoPret;

	@Autowired
	private Configuration freemarkerConfig;

	/*public static void main(String[] args) {
		SpringApplication.run(BiblioBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws MessagingException {
		System.out.println("Sending Email...");
		abonnePret = new AbonnePret();
		ouvrage = new Ouvrage();

		//récupération de l'abonné
		abonnePret = daoAbonnePret.findById(34).get();

		//récupération de l'ouvrage
		ouvrage = daoOuvrage.findByCodeBibliotheque("AEZCON1355");

		envoiEmail(abonnePret, ouvrage);

		System.out.println("Done");
	}

	public void envoiEmail(AbonnePret abonne, Ouvrage ouvrage) throws MessagingException {

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

	}*/

	public static void main(String[] args) {
		SpringApplication.run(BiblioBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws MessagingException, IOException, TemplateException {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("/templates/");

		System.out.println("Sending Email...");
		abonnePret = new AbonnePret();
		ouvrage = new Ouvrage();
		mail = new Mail();

		//récupération de l'abonné
		abonnePret = daoAbonnePret.findById(34).get();

		//récupération de l'ouvrage
		ouvrage = daoOuvrage.findByCodeBibliotheque("AEZCON1355");

		/*mail.setFrom("no-reply@memorynotfound.com");*/
		mail.setTo("alexandre.lardon@yahoo.fr");
		mail.setSubject("Rappel de restitution du livre " + ouvrage.getLivre().getTitre());
		mail.setOuvrage(ouvrage);
		mail.setAbonnePret(abonnePret);

		envoiEmail(mail);

		System.out.println("Done");
	}

	public void envoiEmail(Mail mail) throws MessagingException, IOException, TemplateException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		helper.addAttachment("logo.png", new ClassPathResource("static/logo/Logo_bibliothèque.png"));

		Template template = freemarkerConfig.getTemplate("Email.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail);

		helper.setTo(mail.getTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		/*helper.setFrom(mail.getFrom());*/

		javaMailSender.send(message);
	}
}
