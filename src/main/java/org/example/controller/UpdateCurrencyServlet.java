package org.example.controller;

import org.example.service.CurrencyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/updateCurrency")
public class UpdateCurrencyServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String code = req.getParameter("code");
        String fullName = req.getParameter("fullName");
        String sign = req.getParameter("sign");

        if (code == null || fullName == null || sign == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
            return;
        }

        try {
            CurrencyService currencyService = new CurrencyService();
            currencyService.updateCurrency(id, code, fullName, sign);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Currency updated successfully");
        } catch (Exception e) {
            // Обработка ошибок
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating currency");
        }
    }
}
