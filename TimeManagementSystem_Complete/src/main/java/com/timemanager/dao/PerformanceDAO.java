package com.timemanager.dao;

import com.timemanager.DatabaseConnection;

import java.sql.*;

public class PerformanceDAO {

    public int getTotalUsers() throws SQLException {

        String sql = "SELECT COUNT(*) FROM users";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getInt(1);
        }
    }

    public int getTotalTasks() throws SQLException {

        String sql = "SELECT COUNT(*) FROM tasks";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getInt(1);
        }
    }

    public int getCompletedTasks() throws SQLException {

        String sql =
                "SELECT COUNT(*) FROM tasks WHERE status='COMPLETED'";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getInt(1);
        }
    }

    public int getPendingTasks() throws SQLException {

        String sql =
                "SELECT COUNT(*) FROM tasks WHERE status='PENDING'";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getInt(1);
        }
    }

    public long getTotalTimeLogged() throws SQLException {

        String sql =
                "SELECT COALESCE(SUM(duration_seconds), 0) FROM time_logs";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getLong(1);
        }
    }
}