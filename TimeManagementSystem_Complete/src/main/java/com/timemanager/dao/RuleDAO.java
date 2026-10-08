package com.timemanager.dao;

import com.timemanager.DatabaseConnection;
import java.sql.*;

public class RuleDAO {
    public boolean save(String name,int importanceWeight,int deadlineWeight) throws SQLException {
        try(Connection c=DatabaseConnection.getConnection();
           PreparedStatement ps=c.prepareStatement(
    "INSERT INTO prioritization_rules(rule_name,importance_weight,deadline_weight) VALUES(?,?,?)"
)){
            ps.setString(1,name); ps.setInt(2,importanceWeight); ps.setInt(3,deadlineWeight);
            return ps.executeUpdate()>0;
        }
    }
}
