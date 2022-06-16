package com.afpa.tennis.core.dao;

import com.afpa.tennis.core.DataSourceProvider;
import com.afpa.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDao {
    public void createMatchWithScore(Match match) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDatasource();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

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

            PreparedStatement preparedStatement2 = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement2.setLong(1, match.getScore().getMatch().getId());
            preparedStatement2.setByte(2, match.getScore().getSet1());

            if (match.getScore().getSet2() == null) {
                preparedStatement2.setNull(3, Types.TINYINT); // TinYnt = type en Bd
            } else {
                preparedStatement2.setByte(3, match.getScore().getSet2());
            }

            if (match.getScore().getSet3() == null) {
                preparedStatement2.setNull(4, Types.TINYINT); // TinYnt = type en Bd
            } else {
                preparedStatement2.setByte(4, match.getScore().getSet3());
            }

            if (match.getScore().getSet4() == null) {
                preparedStatement2.setNull(5, Types.TINYINT); // TinYnt = type en Bd
            } else {
                preparedStatement2.setByte(5, match.getScore().getSet4());
            }

            if (match.getScore().getSet5() == null) {
                preparedStatement2.setNull(6, Types.TINYINT); // TinYnt = type en Bd
            } else {
                preparedStatement2.setByte(6, match.getScore().getSet5());
            }

            preparedStatement2.executeUpdate();

            ResultSet rs2 = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                match.getScore().setId(rs.getLong(1));
            }

            System.out.println("Joueur Créé");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

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
}
