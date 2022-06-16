package com.afpa.tennis.core;

import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.entity.Tournoi;
import com.afpa.tennis.core.repository.JoueurRepository;
import com.afpa.tennis.core.repository.TournoiRepository;

import java.util.List;

public class TestTournoiRepository {
    public static void main(String[] args) {
        TournoiRepository tournoiRepository = new TournoiRepository();

        // Create
        Tournoi tournoiTest = new Tournoi();
        tournoiTest.setNom("NomTest");
        tournoiTest.setCode("TE");
        // tournoiRepository.create(tournoiTest);

        System.out.println("L'id du tournoi cré est : " + tournoiTest.getId());

        // Update
        Tournoi tournoi = tournoiRepository.getById(5L);
        tournoi.setNom("Yannick");
        // tournoiRepository.update(tournoi);

        // Delete
        tournoiRepository.delete(5L);

        // Liste
        List<Tournoi> liste = tournoiRepository.liste();
        for (Tournoi tournoi1 :liste){
            System.out.println(tournoi.getNom() + " " + tournoi.getCode());
        }
    }
}
