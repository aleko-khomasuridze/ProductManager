package com.example.productmanager.alekokhomasuridze.util;

import com.example.productmanager.alekokhomasuridze.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }
}
