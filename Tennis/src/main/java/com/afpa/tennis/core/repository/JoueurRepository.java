package com.afpa.tennis.core.repository;

import com.afpa.tennis.core.DataSourceProvider;
import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepository {
    /*public void renomme (Long id, String nouveauNom){
        Joueur joueur = null;
        Session session = null;
        Transaction tx = null;

        try {
            // Récupération session Hibernate
            session = HibernateUtil.getSessionFactory().openSession();

            // Création transaction avant modifications
            tx = session.beginTransaction();

            // 1er parametre : Type à récupérer (Joueur), 2 eme parametre : clé
            joueur = session.get(Joueur.class, id); // joueur est Persistant

            joueur.setNom(nouveauNom);

            // Synchronisation avec la DB (flush)
            tx.commit();

            System.out.println("Joueur renommé");
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
    }*/

    public void create(Joueur joueur) {
            // Récupération session Hibernate
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(joueur);
    }

    public void update(Joueur joueur) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDatasource();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString()); // sexe est un caractère
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate();

            System.out.println("Joueur Modifié");

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
        Joueur joueur = getById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.delete(joueur);
        System.out.println("Joueur Supprimé");
    }

    public Joueur getById(Long id) {
        Joueur joueur = null;
        Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();

        joueur = session.get(Joueur.class, id);

        System.out.println("Joueur lu.");

        return joueur;
    }

    public List<Joueur> liste() {
        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getSingleDatasource();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM JOUEUR");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }


            System.out.println("Joueurs lus.");

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
        return joueurs;
    }
}


