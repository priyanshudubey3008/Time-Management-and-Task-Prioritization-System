package com.timemanager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

public final class DatabaseInitializer {

    private DatabaseInitializer() {}

    public static void initialize() throws Exception {
        try (Connection connection =
                     DatabaseConnection.getServerConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(
                    "CREATE DATABASE IF NOT EXISTS time_management"
            );
        }

        try (InputStream input =
                     DatabaseInitializer.class.getResourceAsStream(
                             "/schema.sql")) {

            if (input == null) {
                throw new IllegalStateException(
                        "schema.sql not found in application resources."
                );
            }

            String sql;
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))) {
                sql = reader.lines()
                        .filter(line -> !line.trim().startsWith("--"))
                        .collect(Collectors.joining("\n"));
            }

            try (Connection connection =
                         DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement()) {

                for (String part : sql.split(";")) {
                    String query = part.trim();

                    if (query.isEmpty()
                            || query.matches("(?is)^\\s*USE\\s+time_management\\s*$")
                            || query.matches("(?is)^\\s*CREATE\\s+DATABASE\\b.*")) {
                        continue;
                    }
                   System.out.println("Executing SQL: " + query);
                }
            }
        }
    }
}
