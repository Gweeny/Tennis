package com.afpa.tennis.controller;

import com.afpa.tennis.core.dto.EpreuveFullDto;
import com.afpa.tennis.core.dto.EpreuveLightDto;
import com.afpa.tennis.core.entity.Epreuve;
import com.afpa.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {
    private EpreuveService epreuveService;

    public EpreuveController(){
        this.epreuveService = new EpreuveService();
    }

    public void afficheDetailsEpreuve(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez récupérer les infos ?");
        long identifiant = scanner.nextLong();

        Epreuve epreuve = epreuveService.getEpreuve(identifiant);
        System.out.println("l'epreuve se déroule en :" + epreuve.getAnnee() + " Et c'est une épreuve " + epreuve.getTypeEpreuve());

    }

    public void afficheDerniereEpreuve(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez récupérer les infos ?");
        long identifiant = scanner.nextLong();
        EpreuveFullDto epreuve = epreuveService.getEpreuveAvecTournoi(identifiant);
        System.out.println("Le nom du tournoi est : " + epreuve.getTournoi().getNom());
    }

    public void afficheRollandGarros(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez récupérer les infos ?");
        long identifiant = scanner.nextLong();
        EpreuveLightDto epreuve = epreuveService.getEpreuveSansTournoi(identifiant);
        // System.out.println("Le nom du tournoi est : " + epreuve.getTournoi().getNom());
    }
}
