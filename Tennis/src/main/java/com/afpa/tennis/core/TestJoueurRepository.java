package com.afpa.tennis.core;

import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.repository.JoueurRepository;

import java.util.List;

public class TestJoueurRepository {
    public static void main(String[] args) {
        JoueurRepository joueurRepository = new JoueurRepository();

        // Create
        Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannickkkkkkk");
        noah.setSexe('H');

        // joueurRepository.create(noah);
        // System.out.println("L'id du joueur cré est : " + noah.getId());


        // getById
        Joueur osaka = joueurRepository.getById(29L);
        System.out.println("Le nom du joueur numéro 29 est " + osaka.getNom() + " " + osaka.getPrenom());

        // Update
        Joueur yNoah = joueurRepository.getById(82L);
        yNoah.setPrenom("Yannick");
        // joueurRepository.update(yNoah);

        // Delete
        // joueurRepository.delete(82L);

        // Liste
        List<Joueur> liste = joueurRepository.liste();
        for (Joueur joueur :liste){
            System.out.println(joueur.getPrenom() + " " + joueur.getNom());
        }
    }
}
