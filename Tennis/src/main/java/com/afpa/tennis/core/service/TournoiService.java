package com.afpa.tennis.core.service;

import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.dto.TournoiDto;
import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.entity.Tournoi;
import com.afpa.tennis.core.repository.JoueurRepository;
import com.afpa.tennis.core.repository.TournoiRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TournoiService {
    private TournoiRepository tournoiRepository;

    public TournoiService(){
        this.tournoiRepository = new TournoiRepository();
    }

    public void create(TournoiDto tournoiDto) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Tournoi tournoi1 = new Tournoi();
            tournoi1.setId(tournoiDto.getId());
            tournoi1.setNom(tournoiDto.getNom());
            tournoi1.setCode(tournoiDto.getCode());

            tournoiRepository.create(tournoi1);
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
    }

    public TournoiDto getTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Tournoi tournoi = null;
        TournoiDto tournoiDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoi = tournoiRepository.getById(id);

            tournoiDto = new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setNom(tournoi.getNom());
            tournoiDto.setCode(tournoi.getCode());

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
        return tournoiDto;
    }

    public void update(Tournoi tournoi) {
        tournoiRepository.update(tournoi);
    }

    public List<Tournoi> liste(){
        return tournoiRepository.liste();
    }

    public void deleteTournoi(Long id){
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            tournoiRepository.delete(id);
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
    }
}
