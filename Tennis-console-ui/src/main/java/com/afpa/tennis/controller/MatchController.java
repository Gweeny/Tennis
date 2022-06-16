package com.afpa.tennis.controller;

import com.afpa.tennis.core.dto.MatchDto;
import com.afpa.tennis.core.dto.TournoiDto;
import com.afpa.tennis.core.service.MatchService;
import com.afpa.tennis.core.service.TournoiService;

import java.util.Scanner;

public class MatchController {
    private MatchService matchService;

    public MatchController(){
        this.matchService = new MatchService();
    }

    public void afficheDetailsMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match dont vous voulez récupérer les infos ?");
        long identifiant = scanner.nextLong();

        MatchDto matchDto = matchService.getMatch(identifiant);
        System.out.println("Le vainqueur est " + matchDto.getVainqueur().getNom() + " " + matchDto.getVainqueur().getPrenom());
        System.out.println("Le finaliste est " + matchDto.getFinaliste().getNom() + " " + matchDto.getFinaliste().getPrenom());

    }
}
