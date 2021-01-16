package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.AbonneBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeController {

    private AbonneBean utilisateurAuthentifie = new AbonneBean();
    private int codeRole = 5;

    @RequestMapping(value = "/Employe")
    public String employe(Model model){

       utilisateurAuthentifie.setPseudo("Régis");

       //ajout dans le model
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);

        return "Employe";
    }


}
