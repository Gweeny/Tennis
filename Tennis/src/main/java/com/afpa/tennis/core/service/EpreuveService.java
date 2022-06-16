package com.afpa.tennis.core.service;

import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.dto.EpreuveFullDto;
import com.afpa.tennis.core.dto.EpreuveLightDto;
import com.afpa.tennis.core.dto.TournoiDto;
import com.afpa.tennis.core.entity.Epreuve;
import com.afpa.tennis.core.repository.EpreuveRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private final EpreuveRepository epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepository();
    }

    public Epreuve getEpreuve(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);

            System.out.println("L'épreuve se déroule en : " + epreuve.getAnnee());

            System.out.println("La classe de la propriété tournoi est : " + epreuve.getTournoi().getId());

            System.out.println("Il s'agit du tournoi de : " + epreuve.getTournoi().getNom());

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
        return epreuve;
    }

    public EpreuveFullDto getEpreuveAvecTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveFullDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);

            dto = new EpreuveFullDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());

            dto.setTournoi(tournoiDto);

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
        return dto;
    }

    public EpreuveLightDto getEpreuveSansTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveLightDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);

            dto = new EpreuveLightDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

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
        return dto;
    }

}
