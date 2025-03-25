package org.example.controller;


import org.example.service.CurrencyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MavenCurrency/addCurrency")
public class addCurrencyServlet extends HttpServlet {

    CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Какие-то вводне значения.
        String code = req.getParameter("code");
        String fullName = req.getParameter("fullName");
        String sign = req.getParameter("sign");

        currencyService.addCurrency(code, fullName, sign);
    }
}
