package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Currency;
import org.example.service.CurrencyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MavenCurrency/getOneCurrency")
public class OneCurrencyServlet extends HttpServlet {

    public CurrencyService currencyService = new CurrencyService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        resp.setCharacterEncoding("UTF-8");

        String code = req.getParameter("code");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Currency currencies = currencyService.getCurrencyByCode(code);
            String jsonResponse = objectMapper.writeValueAsString(currencies);

            PrintWriter out = resp.getWriter();
            out.print(jsonResponse);
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"ошибка подключения к currencies\"}");
        }
    }

}
