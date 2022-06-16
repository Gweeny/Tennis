package com.afpa.tennis.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDeConnection {
    public static void main(String... args) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Paris");

            //MysqlDataSource dataSource = new MysqlDataSource();
            //dataSource.setURL("jdbc:mysql://localhost:3306/tennis?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            // Configuration de la DataSource
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("COURSDB");

            // Initialisation du pool de connexion
            dataSource.setInitialSize(5);

            conn = dataSource.getConnection();

            // Desactivation autoCommit
            // conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR(NOM, PRENOM, SEXE) VALUES(?,?,?) ");

            String nom = "Errani";
            String prenom = "Sara";
            String sexe = "F";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            // 2 eme insertion
            nom = "Johannson";
            prenom = "Thomas";
            sexe = "H";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            // Validation et persistance
            conn.commit();

            System.out.println("success");

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
}
