package com.afpa.tennis.core;

import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.service.JoueurService;

import java.util.List;

public class TestJoueurService {
    public static void main(String[] args) {
        JoueurService joueurService = new JoueurService();

        // Create
        Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannickkkkkkk");
        noah.setSexe('H');

        // joueurService. create(noah);
        // System.out.println("L'id du joueur cré est : " + noah.getId());

        // getById
        Joueur osaka = joueurService.getJoueur(29L);
        System.out.println("Le nom du joueur numéro 29 est " + osaka.getNom() + " " + osaka.getPrenom());

        // Update
        Joueur yNoah = joueurService.getJoueur(83L);
        yNoah.setPrenom("Yannickzzzz");
        // joueurService.update(yNoah);

        // Delete
        // joueurService.delete(83L);

        // Liste
        List<Joueur> liste = joueurService.liste();
        for (Joueur joueur : liste) {
            System.out.println(joueur.getPrenom() + " " + joueur.getNom());
        }
    }
}
