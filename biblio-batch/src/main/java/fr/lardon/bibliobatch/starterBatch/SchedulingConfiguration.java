package fr.lardon.bibliobatch.starterBatch;

import fr.lardon.bibliobatch.controller.BatchController;
import fr.lardon.bibliobatch.dao.DaoAbonnePret;
import fr.lardon.bibliobatch.dao.DaoOuvrage;
import fr.lardon.bibliobatch.model.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
/**
 * classe permettant l'envoi des mails et permettant de fixer un horaire d'envoi
 */
public class SchedulingConfiguration {

    private AbonnePretOuvrage abonnePretOuvrage;
    private Ouvrage ouvrage;
    private Mail mail;
    private List<Pret> pretList;
    private List<AbonnePret> abonnePretList;
    private Pret pretAEnvoyer;
    private String typeEmail = null;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private DaoAbonnePret daoAbonnePret;

    @Autowired
    private DaoOuvrage daoOuvrage;

    @Autowired
    private BatchController batchController;

    @Autowired
    private Configuration freemarkerConfig;

    /*@Scheduled(cron = "0 0 0 * * *")*/
    @Scheduled(fixedRate = 20000L)
    public void startBatch() throws MessagingException, IOException, TemplateException {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");

        System.out.println("Sending Email...");
        abonnePretOuvrage = new AbonnePretOuvrage();
        ouvrage = new Ouvrage();
        mail = new Mail();
        pretAEnvoyer = new Pret();

        //récuperation de tous les abonnés
        abonnePretList = daoAbonnePret.findAll();

        for(AbonnePret abonnePret : abonnePretList){
            abonnePretOuvrage = batchController.abonnePretSelonSonId(abonnePret.getIdAbonne());
            pretList = abonnePretOuvrage.getListePret();

            for(Pret pret : pretList){
                //envoi de l'email
                if(pret.getStatutPriorite() == "1" || pret.getStatutPriorite() == "2") {
                    //type d'email à envoyé
                    if(pret.getStatutPriorite() == "1"){
                        typeEmail = "Email.ftl";
                    }else if(pret.getStatutPriorite() == "2"){
                        typeEmail = "EmailRappel.ftl";
                    }

                    pretAEnvoyer = pret;

                    //récupération de l'ouvrage
                    ouvrage = daoOuvrage.findByCodeBibliotheque(pretAEnvoyer.getOuvragePret().getCodeBibliotheque());

                    mail.setTo(abonnePretOuvrage.getAbonne().getEmail());
                    mail.setSubject("Rappel de restitution du livre '" + ouvrage.getLivre().getTitre() + "'");
                    mail.setOuvrage(ouvrage);
                    mail.setPret(pretAEnvoyer);

                    envoiEmail(mail);
                }
            }
        }
        System.out.println("Done");
    }

    public void envoiEmail(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.addAttachment("logo.png", new ClassPathResource("static/logo/Logo_bibliothèque.png"));

        Template template = freemarkerConfig.getTemplate(typeEmail);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());

        javaMailSender.send(message);
    }



}
