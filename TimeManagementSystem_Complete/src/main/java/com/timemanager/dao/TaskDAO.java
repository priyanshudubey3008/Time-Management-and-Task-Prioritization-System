package com.timemanager.dao;

import com.timemanager.DatabaseConnection;
import com.timemanager.model.Task;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements Repository<Task, Integer> {
    public int add(Task t) throws SQLException {
        String sql = "INSERT INTO tasks(user_id,task_name,description,deadline,importance,category_id,status,priority_score) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection c=DatabaseConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,t.getUserId()); ps.setString(2,t.getTaskName());
            ps.setString(3,t.getDescription()); ps.setDate(4,Date.valueOf(t.getDeadline()));
            ps.setString(5,t.getImportance());
            if(t.getCategoryId()==null) ps.setNull(6,Types.INTEGER); else ps.setInt(6,t.getCategoryId());
            ps.setString(7,t.getStatus()); ps.setDouble(8,t.getPriorityScore());
            ps.executeUpdate();
            ResultSet keys=ps.getGeneratedKeys();
            return keys.next()?keys.getInt(1):0;
        }
    }

    public List<Task> findByUser(int userId) throws SQLException {
        List<Task> list=new ArrayList<>();
        String sql="SELECT * FROM tasks WHERE user_id=? ORDER BY priority_score DESC, deadline";
        try(Connection c=DatabaseConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,userId); ResultSet rs=ps.executeQuery();
            while(rs.next()) list.add(map(rs));
        }
        return list;
    }

    public boolean updateStatus(int id,String status) throws SQLException {
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("UPDATE tasks SET status=? WHERE task_id=?")){
            ps.setString(1,status); ps.setInt(2,id); return ps.executeUpdate()>0;
        }
    }

    public boolean delete(Integer id) throws SQLException {
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("DELETE FROM tasks WHERE task_id=?")){
            ps.setInt(1,id); return ps.executeUpdate()>0;
        }
    }

    public Task findById(Integer id) throws SQLException {
        try(Connection c=DatabaseConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("SELECT * FROM tasks WHERE task_id=?")){
            ps.setInt(1,id); ResultSet rs=ps.executeQuery(); return rs.next()?map(rs):null;
        }
    }

    public List<Task> findAll() throws SQLException {
        List<Task> all=new ArrayList<>();
        try(Connection c=DatabaseConnection.getConnection();
            Statement st=c.createStatement(); ResultSet rs=st.executeQuery("SELECT * FROM tasks")){
            while(rs.next()) all.add(map(rs));
        }
        return all;
    }

    private Task map(ResultSet rs) throws SQLException {
        return new Task(rs.getInt("task_id"),rs.getInt("user_id"),
            rs.getString("task_name"),rs.getString("description"),
            rs.getDate("deadline").toLocalDate(),rs.getString("importance"),
            (Integer)rs.getObject("category_id"),rs.getString("status"),
            rs.getDouble("priority_score"));
    }
}
