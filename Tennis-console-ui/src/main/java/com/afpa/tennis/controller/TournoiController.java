package com.afpa.tennis.controller;

import com.afpa.tennis.core.dto.TournoiDto;
import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.entity.Tournoi;
import com.afpa.tennis.core.service.JoueurService;
import com.afpa.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {
    private TournoiService tournoiService;

    public TournoiController(){
        this.tournoiService = new TournoiService();
    }

    public void afficheDetailsTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi dont vous voulez récupérer les infos ?");
        long identifiant = scanner.nextLong();

        TournoiDto tournoi = tournoiService.getTournoi(identifiant);
        System.out.println("Il s'agit du tournoi de " + tournoi.getNom());
    }

    public void creerTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom tournoi à créer ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le code du tournoi à créer ?");
        String  code = scanner.nextLine();

        TournoiDto tournoi = new TournoiDto();
        tournoi.setNom(nom);
        tournoi.setCode(code);

        tournoiService.create(tournoi);
        System.out.println("Le tournoi a été créé, son id est : " + tournoi.getId());
    }

    public void deleteTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'Id du tournoi que vous voulez supprimer ?");
        Long id = scanner.nextLong();
        scanner.nextLine();

        tournoiService.deleteTournoi(id);
    }
}
