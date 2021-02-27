package fr.lardon.bibliobatch;

import fr.lardon.bibliobatch.controller.BatchController;
import fr.lardon.bibliobatch.dao.DaoAbonnePret;
import fr.lardon.bibliobatch.dao.DaoOuvrage;
import fr.lardon.bibliobatch.dao.DaoPret;
import fr.lardon.bibliobatch.model.AbonnePretOuvrage;
import fr.lardon.bibliobatch.model.Mail;
import fr.lardon.bibliobatch.model.Ouvrage;
import fr.lardon.bibliobatch.model.Pret;
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
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@EnableBatchProcessing
@SpringBootApplication
public class BiblioBatchApplication implements CommandLineRunner {

	AbonnePretOuvrage abonnePretOuvrage;
	Ouvrage ouvrage;
	Mail mail;
	List<Pret> pretList;
	Pret pretAEnvoyer;
	String logo = null;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	DaoAbonnePret daoAbonnePret;

	@Autowired
	DaoOuvrage daoOuvrage;

	@Autowired
	DaoPret daoPret;

	@Autowired
	BatchController batchController;

	@Autowired
	private Configuration freemarkerConfig;


	public static void main(String[] args) {
		SpringApplication.run(BiblioBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws MessagingException, IOException, TemplateException {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("/templates/");

		System.out.println("Sending Email...");
		abonnePretOuvrage = new AbonnePretOuvrage();
		ouvrage = new Ouvrage();
		mail = new Mail();
		pretAEnvoyer = new Pret();
		logo = "static/logo/gotham.png";

		abonnePretOuvrage = batchController.abonnePretSelonSonId(34);
		pretList = abonnePretOuvrage.getListePret();

		System.out.println(abonnePretOuvrage);

		//récupération de l'ouvrage
		ouvrage = daoOuvrage.findByCodeBibliotheque("AEZCON1355");

		for(Pret pret : pretList){
			if(pret.getIdPret() == 69){
				pretAEnvoyer = pret;
			}
		}

		/*mail.setFrom("no-reply@memorynotfound.com");*/
		mail.setTo("alexandre.lardon@yahoo.fr");
		mail.setSubject("Rappel de restitution du livre " + ouvrage.getLivre().getTitre());
		mail.setOuvrage(ouvrage);
		mail.setPret(pretAEnvoyer);
		mail.setLogo(logo);

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
