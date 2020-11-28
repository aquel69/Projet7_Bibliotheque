package com.lardon.projet7bibliotheque.model.bibliotheque;

import java.util.Date;

public class Pret {
    private int idPret;
    private Ouvrage ouvrage;
    private Date dateEmprunt;
    private Date dateRestitution;
    private boolean prolongation;
}
