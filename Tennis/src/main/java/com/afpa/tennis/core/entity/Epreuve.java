package com.afpa.tennis.core.entity;

import javax.persistence.*;

@Entity
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id généré par Mysql et auto-incrémenté
    private Long id;

    private Short annee;

@ManyToOne (fetch = FetchType.LAZY) // Par défaut, fetchType => Eager Permet d'inserer la propriété tournoi dans l'épreuve
    @JoinColumn(name = "ID_TOURNOI") // Donner le bon nom de la table au tournoi
    private Tournoi tournoi;

    @Column(name = "TYPE_EPREUVE", nullable = false, length = 20)
    private Character typeEpreuve;

    public Epreuve() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
}
