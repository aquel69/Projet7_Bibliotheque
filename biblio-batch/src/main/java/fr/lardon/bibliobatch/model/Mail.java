package fr.lardon.bibliobatch.model;

public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;
    private Ouvrage ouvrage;
    private AbonnePret abonnePret;

    public Mail() {
    }

    public Mail(String from, String to, String subject, String content, Ouvrage ouvrage, AbonnePret abonnePret) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.ouvrage = ouvrage;
        this.abonnePret = abonnePret;
    }

    public AbonnePret getAbonnePret() {
        return abonnePret;
    }

    public void setAbonnePret(AbonnePret abonnePret) {
        this.abonnePret = abonnePret;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
