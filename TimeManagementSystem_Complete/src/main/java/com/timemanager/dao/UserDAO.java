// package com.timemanager.dao;

// import com.timemanager.DatabaseConnection;
// import com.timemanager.model.User;
// import java.sql.*;

// public class UserDAO {
//     public User login(String email, String password) throws SQLException {
//         String sql = "SELECT * FROM users WHERE email=? AND password=?";
//         try (Connection c = DatabaseConnection.getConnection();
//              PreparedStatement ps = c.prepareStatement(sql)) {
//             ps.setString(1, email);
//             ps.setString(2, password);
//             ResultSet rs = ps.executeQuery();
//             if (rs.next()) {
//                 return new User(rs.getInt("user_id"), rs.getString("name"),
//                         rs.getString("email"), rs.getString("password"),
//                         rs.getString("role"));
//             }
//         }
//         return null;
//     }
//     public boolean register(String name, String email, String password) throws SQLException {

//     String sql = "INSERT INTO users(name, email, password, role) VALUES(?,?,?,'USER')";

//     try (Connection c = DatabaseConnection.getConnection();
//          PreparedStatement ps = c.prepareStatement(sql)) {

//         ps.setString(1, name);
//         ps.setString(2, email);
//         ps.setString(3, password);

//         return ps.executeUpdate() > 0;
//     }
// }
// }


package com.timemanager.dao;

import com.timemanager.DatabaseConnection;
import com.timemanager.model.User;
import java.sql.*;

public class UserDAO {

    public User login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        }

        return null;
    }

    public boolean register(String name, String email,
                            String password, String role) throws SQLException {

        String sql =
                "INSERT INTO users(name, email, password, role) VALUES(?,?,?,?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);

            return ps.executeUpdate() > 0;
        }
    }
}