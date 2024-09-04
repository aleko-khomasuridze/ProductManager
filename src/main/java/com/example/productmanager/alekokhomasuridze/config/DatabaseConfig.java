package com.example.productmanager.alekokhomasuridze.config;


import com.example.productmanager.alekokhomasuridze.Logger.Logger;
import com.example.productmanager.alekokhomasuridze.Logger.Module.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Database configuration class that provides a method to get a database connection using basic JDBC.
 */
public class DatabaseConfig {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/ProductData";
    // Database credentials
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    static {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger.log.Error(e.getMessage());
        }
    }

    /**
     * Establishes and returns a new database connection.
     *
     * @return A new Connection object
     * @throws SQLException If a database access error occurs or the url is null
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

