package com.afpa.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static BasicDataSource singleDataSource;

    public static DataSource getSingleDatasource() {
        if (singleDataSource == null) {

            singleDataSource = new BasicDataSource();
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Paris");

            // Configuration de la DataSource
            singleDataSource.setUsername("COURSDB");
            singleDataSource.setPassword("COURSDB");

            // Initialisation du pool de connexion
            singleDataSource.setInitialSize(5);
        }

        return singleDataSource;
    }
}
