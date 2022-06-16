package com.afpa.tennis.controller;

import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {
    private JoueurService joueurService;

    public JoueurController(){
        this.joueurService = new JoueurService();
    }

    public void afficheDetailsJoueur(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez récupérer les infos ?");
        long identifiant = scanner.nextLong();

        Joueur joueur = joueurService.getJoueur(identifiant);
        System.out.println("Le joueur s'appelle " + joueur.getNom() + " " + joueur.getPrenom());
    }

    public void creerJoueur(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom du joueur que vous voulez créer ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le prénom du joueur que vous voulez créer ?");
        String  prenom = scanner.nextLine();
        System.out.println("Quel est le sexe du joueur que vous voulez créer ?");
        char  sexe = scanner.nextLine().charAt(0);

        Joueur joueur = new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);

        joueurService.create(joueur);
        System.out.println("Le joueur a été créé, son id est : " + joueur.getId());
    }

    public void renomme(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'Id du joueur que vous voulez renommer ?");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau nom du joueur ?");
        String nouveaunom = scanner.nextLine();

        joueurService.renomme(id, nouveaunom);
    }

    public void changeSexe(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'Id du joueur dont vous voulez changer le sexe ?");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau sexe du joueur ?");
        char nouveauSexe = scanner.nextLine().charAt(0);

        joueurService.changeSexe(id, nouveauSexe);
    }

    public void deleteJoueur(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'Id du joueur que vous voulez supprimer ?");
        Long id = scanner.nextLong();
        scanner.nextLine();

        joueurService.deleteJoueur(id);
    }
}
