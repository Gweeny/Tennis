package com.afpa.tennis.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "MATCH_TENNIS")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch =  FetchType.LAZY)
    @JoinColumn(name = "ID_VAINQUEUR")
    private Joueur vainqueur;

    @ManyToOne (fetch =  FetchType.LAZY)
    @JoinColumn(name = "ID_FINALISTE")
    private Joueur finaliste;

    @Transient
    private Epreuve epreuve;

    @Transient
    private Score score;

    public Match() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
