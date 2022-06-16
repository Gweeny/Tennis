package com.afpa.tennis.core.service;

import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.dao.MatchDao;
import com.afpa.tennis.core.dto.EpreuveLightDto;
import com.afpa.tennis.core.dto.JoueurDto;
import com.afpa.tennis.core.dto.MatchDto;
import com.afpa.tennis.core.entity.Epreuve;
import com.afpa.tennis.core.entity.Match;
import com.afpa.tennis.core.repository.MatchRepository;
import com.afpa.tennis.core.repository.ScoreRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {
    private ScoreRepository scoreRepository;
    private MatchRepository matchRepository;
    // private MatchDao matchDao;

    public MatchService(){
       this.scoreRepository = new ScoreRepository();
       this.matchRepository = new MatchRepository();
       // this.matchDao = new MatchDao();
    }

    public void enregistrerNouveauMatch(Match match){
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }

    public void modifierMatchWithScore(Match match){
        // matchDao.createMatchWithScore(match);
    }

    public MatchDto getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match = null;
        MatchDto matchDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            matchDto = new MatchDto();
            matchDto.setId(match.getId());

            JoueurDto vainqueurDto = new JoueurDto();
            vainqueurDto.setId(match.getVainqueur().getId());
            vainqueurDto.setNom(match.getVainqueur().getNom());
            vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDto.setSexe(match.getVainqueur().getSexe());

            JoueurDto finalisteDto = new JoueurDto();
            finalisteDto.setId(match.getFinaliste().getId());
            finalisteDto.setNom(match.getFinaliste().getNom());
            finalisteDto.setPrenom(match.getFinaliste().getPrenom());
            finalisteDto.setSexe(match.getFinaliste().getSexe());

            matchDto.setVainqueur(vainqueurDto);
            matchDto.setFinaliste(finalisteDto);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return matchDto;
    }
}
