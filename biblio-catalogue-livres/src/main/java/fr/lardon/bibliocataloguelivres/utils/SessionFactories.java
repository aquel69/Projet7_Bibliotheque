package fr.lardon.bibliocataloguelivres.utils;

import fr.lardon.bibliocataloguelivres.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactories<T> {
    private static SessionFactory sessionFactory;
    public static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure(HIBERNATE_CFG_XML)

                    .addAnnotatedClass(AbonnePret.class)
                    .addAnnotatedClass(Auteur.class)
                    .addAnnotatedClass(Editeur.class)
                    .addAnnotatedClass(Genre.class)
                    .addAnnotatedClass(Livre.class)
                    .addAnnotatedClass(Ouvrage.class)
                    .addAnnotatedClass(Pret.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
