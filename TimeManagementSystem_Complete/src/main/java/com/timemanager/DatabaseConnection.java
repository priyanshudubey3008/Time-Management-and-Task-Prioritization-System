package com.timemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final String SERVER_URL =
            "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";

    private static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/time_management?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD") != null
                    ? System.getenv("DB_PASSWORD")
                    : "";

    private DatabaseConnection() {
    }

    public static Connection getServerConnection() throws SQLException {
        return DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection.isValid(2);
        } catch (SQLException e) {
            return false;
        }
    }
}
