package org.example.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.DatabaseConnectionManager;
import org.example.Main;
import org.example.database.DatabaseConnection;
import org.example.model.Currency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService {

    private static final String JDBC_URL = "jdbc:sqlite:C:/Dev/MavenCurrency/src/main/resources/database.db";


    public List<Currency> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:sqlite:C:/Dev/MavenCurrency/src/main/resources/database.db");

            HikariDataSource dataSource = new HikariDataSource(config);
            connection = dataSource.getConnection();

            statement = connection.createStatement();

            String query = "SELECT * FROM main.currencies";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Currency currency = new Currency(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getString("fullName"),
                        resultSet.getString("sign")
                );
                currencies.add(currency);
            }
            return currencies;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return currencies;
    }

    public Currency getCurrencyByCode(String code) {
        //Class.forName("org.sqlite.JDBC");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:C:/Dev/MavenCurrency/src/main/resources/database.db");

        HikariDataSource dataSource = new HikariDataSource(config);

        String query = "SELECT * FROM main.currencies WHERE code = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, code);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Currency(
                            resultSet.getInt("id"),
                            resultSet.getString("code"),
                            resultSet.getString("fullName"),
                            resultSet.getString("sign")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

    public void addCurrency(String code, String fullName, String sign) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:C:/Dev/MavenCurrency/src/main/resources/database.db");

        HikariDataSource dataSource = new HikariDataSource(config);

        String query = "INSERT IGNORE INTO main.currencies (code, fullName, sign) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, sign);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void updateCurrency(int id, String code, String fullName, String sign) {
        /*
        String query = "SELECT * FROM currencies WHERE code = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        }

         */
    }

}
