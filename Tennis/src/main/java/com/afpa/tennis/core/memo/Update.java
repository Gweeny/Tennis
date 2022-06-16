package com.afpa.tennis.core.memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    public static void main(String... args) {
        Connection conn = null;
        try {
            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Paris", "COURSDB", "COURSDB");

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?");

            long identifiant = 20L;
            String nom = "Dupond";
            String prenom = "René";

            // Valorisation des ? (dans l'ordre des index du PreparedStatement)
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setLong(3, identifiant);

            // preparedStatement.executeUpdate() retourne le nb d'enregistrements modifiée (C,U,D)
            int nbEnregistremetsModifies = preparedStatement.executeUpdate();
            System.out.println(nbEnregistremetsModifies + " enregistrements ont été modifiés");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
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

