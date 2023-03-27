package com.example.myfirstapp;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CinemaDAO cinemaDAO = new CinemaDAO();
        String message = "";
        if (request.getParameter("count") != null){
            Integer count = Integer.valueOf(request.getParameter("count"));
            Integer id = Integer.valueOf(request.getParameter("id"));
            try {
                message = cinemaDAO.buy(count, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        List<Cinema> cinemas = null;
        try {
            cinemas = cinemaDAO.getAll();
            cinemas.stream().filter(c -> c.getCount() == 0).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("cinemas", cinemas);
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/buy.jsp");
        requestDispatcher.forward(request, response);
    }

}