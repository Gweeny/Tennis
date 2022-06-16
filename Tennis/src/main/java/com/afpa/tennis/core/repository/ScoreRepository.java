package com.afpa.tennis.core.repository;

import com.afpa.tennis.core.DataSourceProvider;
import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.entity.Joueur;
import com.afpa.tennis.core.entity.Score;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreRepository {
    public void create(Score score) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDatasource();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, score.getMatch().getId());
            preparedStatement.setByte(2, score.getSet1());

            if (score.getSet2() == null){
                preparedStatement.setNull(3, Types.TINYINT); // TinYnt = type en Bd
            }
            else {
                preparedStatement.setByte(3, score.getSet2());
            }

            if (score.getSet3() == null){
                preparedStatement.setNull(4, Types.TINYINT); // TinYnt = type en Bd
            }
            else {
                preparedStatement.setByte(4, score.getSet3());
            }

            if (score.getSet4() == null){
                preparedStatement.setNull(5, Types.TINYINT); // TinYnt = type en Bd
            }
            else {
                preparedStatement.setByte(5, score.getSet4());
            }

            if (score.getSet5() == null){
                preparedStatement.setNull(6, Types.TINYINT); // TinYnt = type en Bd
            }
            else {
                preparedStatement.setByte(6, score.getSet5());
            }

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                score.setId(rs.getLong(1));
            }

            System.out.println("Joueur Créé");

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

    public Score getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Score score = session.get(Score.class, id);

        System.out.println("score lu.");

        return score;
    }
}


