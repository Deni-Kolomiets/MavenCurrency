package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

    public static void executeUpdate(String query) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQuery(String query) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Пример получения данных из колонки "name"
                String name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод с параметрами
    public static void executeQueryWithParams(String query, Object... params) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Пример получения данных из колонки "name"
                String name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}