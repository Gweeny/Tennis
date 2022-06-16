package com.afpa.tennis.core.memo;

import java.sql.*;

public class Select {
    public static void main(String... args) {
        Connection conn = null;
        try {
            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Paris", "COURSDB", "COURSDB");


            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, PRENOM FROM JOUEUR WHERE ID = ?");

            long identifiant = 20L;
            preparedStatement.setLong(1, identifiant);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                final String nom = rs.getString("NOM");
                final String prenom = rs.getString("PRENOM");
                final Long id = rs.getLong("ID");
                System.out.println("Le joueur numero " + id + " est " + nom + " " + prenom + ".");
            }

            preparedStatement.setLong(1, 40L);
            ResultSet rs2 = preparedStatement.executeQuery();

            while (rs2.next()) {
                final String nom = rs2.getString("NOM");
                final String prenom = rs2.getString("PRENOM");
                final Long id = rs2.getLong("ID");
                System.out.println("Le joueur numero " + id + " est " + nom + " " + prenom + ".");
            }


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

