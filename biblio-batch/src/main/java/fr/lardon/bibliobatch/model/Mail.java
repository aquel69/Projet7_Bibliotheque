package fr.lardon.bibliobatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;
    private Ouvrage ouvrage;
    private AbonnePret abonnePret;
    private Pret pret;
    private Bibliotheque bibliotheque;

}
