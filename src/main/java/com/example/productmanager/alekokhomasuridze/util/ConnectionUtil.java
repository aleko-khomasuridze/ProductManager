package com.example.productmanager.alekokhomasuridze.util;

import com.example.productmanager.alekokhomasuridze.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 * <p>
 * This class provides a static method to obtain a database connection
 * using the configuration specified in the {@link DatabaseConfig} class.
 * </p>
 */
public class ConnectionUtil {

    /**
     * Retrieves a database connection.
     * <p>
     * This method delegates the connection creation to the {@link DatabaseConfig#getConnection()} method,
     * which handles the details of connecting to the database.
     * </p>
     *
     * @return A {@link Connection} object representing the database connection.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }
}
