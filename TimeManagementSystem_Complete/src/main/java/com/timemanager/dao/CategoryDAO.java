// package com.timemanager.dao;

// import com.timemanager.DatabaseConnection;
// import com.timemanager.model.Category;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class CategoryDAO {
//     public List<Category> findAll() throws SQLException {
//         List<Category> list=new ArrayList<>();
//         try(Connection c=DatabaseConnection.getConnection();
//             Statement st=c.createStatement();
//             ResultSet rs=st.executeQuery("SELECT * FROM categories ORDER BY name")){
//             while(rs.next()) list.add(new Category(rs.getInt("category_id"),
//                     rs.getString("name"),rs.getString("description")));
//         }
//         return list;
//     }

//     public boolean add(String name,String description) throws SQLException {
//         try(Connection c=DatabaseConnection.getConnection();
//             PreparedStatement ps=c.prepareStatement("INSERT INTO categories(name,description) VALUES(?,?)")){
//             ps.setString(1,name); ps.setString(2,description); return ps.executeUpdate()>0;
//         }
//     }

//     public boolean delete(int id) throws SQLException {
//         try(Connection c=DatabaseConnection.getConnection();
//             PreparedStatement ps=c.prepareStatement("DELETE FROM categories WHERE category_id=?")){
//             ps.setInt(1,id); return ps.executeUpdate()>0;
//         }
//     }
// }


// package com.timemanager.dao;

// import com.timemanager.DatabaseConnection;
// import com.timemanager.model.Category;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class CategoryDAO {

//     public List<Category> findAll() throws SQLException {
//         List<Category> list = new ArrayList<>();

//         try (Connection c = DatabaseConnection.getConnection();
//              Statement st = c.createStatement();
//              ResultSet rs = st.executeQuery(
//                  "SELECT category_id, category_name FROM categories ORDER BY category_name")) {

//             while (rs.next()) {
//                 list.add(new Category(
//                     rs.getInt("category_id"),
//                     rs.getString("category_name")
//                 ));
//             }
//         }

//         return list;
//     }

//     public boolean add(String name, String description) throws SQLException {
//         try (Connection c = DatabaseConnection.getConnection();
//              PreparedStatement ps = c.prepareStatement(
//                  "INSERT INTO categories(category_name) VALUES(?)")) {

//             ps.setString(1, name);
//             return ps.executeUpdate() > 0;
//         }
//     }

//     public boolean delete(int id) throws SQLException {
//         try (Connection c = DatabaseConnection.getConnection();
//              PreparedStatement ps = c.prepareStatement(
//                  "DELETE FROM categories WHERE category_id=?")) {

//             ps.setInt(1, id);
//             return ps.executeUpdate() > 0;
//         }
//     }
// }










package com.timemanager.dao;

import com.timemanager.DatabaseConnection;
import com.timemanager.model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<Category> findAll() throws SQLException {
        List<Category> list = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(
                 "SELECT category_id, category_name FROM categories ORDER BY category_name")) {

            while (rs.next()) {
                list.add(new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    ""
                ));
            }
        }

        return list;
    }

    public boolean add(String name, String description) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                 "INSERT INTO categories(category_name) VALUES(?)")) {

            ps.setString(1, name);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                 "DELETE FROM categories WHERE category_id=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}