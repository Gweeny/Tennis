package com.afpa.tennis.controller;

import com.afpa.tennis.core.entity.Score;
import com.afpa.tennis.core.service.ScoreService;

import java.util.Scanner;

public class ScoreController {
    private ScoreService scoreService;
    public  ScoreController(){
        this.scoreService = new ScoreService();
    }
    public void afficheDetailsScore(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id du score ?");
        long identifiant = scanner.nextLong();

        Score score = scoreService.getScore(identifiant);
        System.out.println("Les sets du score sont : ");
        System.out.println("Set 1 : " + score.getSet1());
        System.out.println("Set 3 : " + score.getSet2());
        if(score.getSet3() !=null){
            System.out.println("Set 3 : " + score.getSet3());
        }
        if(score.getSet4() !=null){
            System.out.println("Set 4 : " + score.getSet4());
        }
        if(score.getSet5() !=null){
            System.out.println("Set 5 : " + score.getSet5());
        }


    }
}
