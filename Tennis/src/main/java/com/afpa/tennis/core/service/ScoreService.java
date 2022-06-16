package com.afpa.tennis.core.service;

import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.entity.Score;
import com.afpa.tennis.core.repository.ScoreRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {
    private final ScoreRepository scoreRepository;

    public ScoreService(){
        this.scoreRepository = new ScoreRepository();
    }

    public Score getScore(Long id) {
        Session session = null;
        Transaction tx = null;
        Score score = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepository.getById(id);
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
        return score;
    }
}
