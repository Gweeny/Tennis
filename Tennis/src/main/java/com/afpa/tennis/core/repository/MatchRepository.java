package com.afpa.tennis.core.repository;

import com.afpa.tennis.core.DataSourceProvider;
import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.entity.Match;
import com.afpa.tennis.core.entity.Score;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepository {
    public void create(Match match) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDatasource();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                match.setId(rs.getLong(1));
            }

            System.out.println("Match Créé");

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

    public Match getById(Long id) {
        Match match = null;
        Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();

        match = session.get(Match.class, id);

        System.out.println("Joueur lu.");

        return match;
    }
}


