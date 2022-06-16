package com.afpa.tennis.core.service;

import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.repository.JoueurRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JoueurService {
    private final JoueurRepository joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepository();
    }

    public void changeSexe(Long id, char nouveauSexe){
        Session session = null;
        Transaction tx = null;

        Joueur joueur = getJoueur(id);

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setSexe(nouveauSexe);
            session.merge(joueur);
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

    public void renomme(Long id, String nouveauNom) {
        Session session = null;
        Transaction tx = null;

        Joueur joueur = getJoueur(id); // Le joueur est détaché

        // Récupération des valeurs courantes en DB
        // Joueur joueur = getJoueur(id);

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setNom(nouveauNom);
            session.merge(joueur);
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

    public void create(Joueur joueur) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueurRepository.create(joueur);
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

    public Joueur getJoueur(Long id) {
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(id);
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
        return joueur;
    }

    public void update(Joueur joueur) {
        joueurRepository.update(joueur);
    }

    public List<Joueur> liste() {
        return joueurRepository.liste();
    }

    public void deleteJoueur(Long id){
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueurRepository.delete(id);
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
