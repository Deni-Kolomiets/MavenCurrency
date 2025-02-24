package org.example.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "MainPageServlet", urlPatterns = {"/"})
public class MainPageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/index1.jsp";

        // Получаем RequestDispatcher для перенаправления запроса
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);

        // Перенаправляем запрос и ответ на указанный путь
        dispatcher.forward(req, resp);
    }

}