package org.example;

import com.google.gson.Gson;
import org.example.database.DatabaseConnection;
import org.example.database.DatabaseUtil;
import org.example.model.Currency;
import org.example.service.CurrencyService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        String JDBC_URL = "jdbc:sqlite:C:/Dev/MavenCurrency/src/main/resources/database.db";

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
    }
}

