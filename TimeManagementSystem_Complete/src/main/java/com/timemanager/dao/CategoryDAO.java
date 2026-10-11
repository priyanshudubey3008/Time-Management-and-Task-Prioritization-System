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
    String sql = "INSERT INTO categories(category_name) VALUES(?)";

    try (Connection c = DatabaseConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, name.trim());

        try {
            return ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new SQLException(
                    "Category '" + name + "' already exists. Please use a different name."
                );
            }
            throw ex;
        }
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
