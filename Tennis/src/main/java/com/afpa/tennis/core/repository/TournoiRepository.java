package com.afpa.tennis.core.repository;

import com.afpa.tennis.core.DataSourceProvider;
import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.entity.Tournoi;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepository {
    public void create(Tournoi tournoi) {
        Session session = null;
        Transaction tx = null;

        try {
            // Récupération session Hibernate
            session = HibernateUtil.getSessionFactory().openSession();

            // Création transaction avant insertions
            tx = session.beginTransaction();

            // Mise en session de l'objet
            session.persist(tournoi);

            // Synchronisation avec la DB
            tx.commit();

            System.out.println("Tournoi Créé");

        } catch (Throwable e) {
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDatasource();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?, CODE=? WHERE ID=?");

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3, tournoi.getId());

            preparedStatement.executeUpdate();

            System.out.println("Tournoi Modifié");

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long id) {
        Tournoi tournoi = getById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.delete(tournoi);
        System.out.println("Tournoi Supprimé");
    }

    public Tournoi getById(Long id) {
        Tournoi tournoi = null;
        Session session = null;

        try {
            // Récupération session Hibernate
            session = HibernateUtil.getSessionFactory().openSession();

            tournoi = session.get(Tournoi.class, id);
            System.out.println("Tournoi lu");
        }
        catch (Throwable t){
            t.printStackTrace();
        }

        finally {
            if (session != null){
                session.close();
            }
        }

        return tournoi;
    }

    public List<Tournoi> liste() {
        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getSingleDatasource();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, CODE FROM TOURNOI");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));

                tournois.add(tournoi);
            }

            System.out.println("Tournois lus.");

        } catch (
                SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournois;
    }
}
