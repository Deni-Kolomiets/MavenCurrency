package org.example.service;

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

//private static final String JDBC_URL = "jdbc:mysql://localhost:3306/currencies";
//private static final String JDBC_USER = "root";
//private static final String JDBC_PASSWORD = "12345";

    private static final String JDBC_URL = "jdbc:sqlite:C:/Dev/MavenCurrency/src/main/resources/database.db";


    public List<Currency> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL);
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
            System.out.println(currencies + "Сервис");
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
        String query = "SELECT * FROM currencies WHERE code = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
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


    public void updateCurrency(int id, String code, String fullName, String sign) {
        /*
        String query = "SELECT * FROM currencies WHERE code = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        }

         */
    }

}
