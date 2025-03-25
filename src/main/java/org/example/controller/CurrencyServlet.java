package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Currency;
import org.example.service.CurrencyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



@WebServlet("/MavenCurrencies/currencies")
public class CurrencyServlet extends HttpServlet {

    public CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");

        resp.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Currency> currencies = currencyService.getCurrencies();
            String jsonResponse = objectMapper.writeValueAsString(currencies);

            PrintWriter out = resp.getWriter();
            out.print(jsonResponse);
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"ошибка подключения к currencies\"}");
        }
    }

    /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Currency> currencies = currencyService.getCurrencies();
            String jsonResponse = new Gson().toJson(currencies);

            PrintWriter out = resp.getWriter();
            out.print(jsonResponse);
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"ошибка подключения к currencies\"}");
        }
    }

     */
}

