package com.timemanager.dao;

import com.timemanager.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;

public class TimeLogDAO {
    public int start(int taskId) throws SQLException {
        String sql="INSERT INTO time_logs(task_id,start_time) VALUES(?,?)";
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,taskId); ps.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate(); ResultSet keys=ps.getGeneratedKeys();
            return keys.next()?keys.getInt(1):0;
        }
    }

    public void stop(int logId) throws SQLException {
        String sql="UPDATE time_logs SET end_time=?,duration_seconds=TIMESTAMPDIFF(SECOND,start_time,?) WHERE log_id=? AND end_time IS NULL";
        try(Connection c=DatabaseConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            Timestamp now=Timestamp.valueOf(LocalDateTime.now());
            ps.setTimestamp(1,now); ps.setTimestamp(2,now); ps.setInt(3,logId); ps.executeUpdate();
        }
    }

    public long totalSecondsForUser(int userId) throws SQLException {
        String sql="SELECT COALESCE(SUM(tl.duration_seconds),0) FROM time_logs tl JOIN tasks t ON tl.task_id=t.task_id WHERE t.user_id=?";
        try(Connection c=DatabaseConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,userId); ResultSet rs=ps.executeQuery(); rs.next(); return rs.getLong(1);
        }
    }
    public long totalSecondsForTask(int taskId) throws SQLException {

    String sql = "SELECT COALESCE(SUM(duration_seconds),0) " +
                 "FROM time_logs WHERE task_id=?";

    try (Connection c = DatabaseConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, taskId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getLong(1);
        }
    }

    return 0;
}
}
