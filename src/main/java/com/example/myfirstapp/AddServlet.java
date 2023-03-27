package com.example.myfirstapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add")
public class AddServlet  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer count = Integer.valueOf(request.getParameter("count"));
        String movieName = request.getParameter("movieName");
        Cinema cinema = new Cinema();
        cinema.setMovieName(movieName);
        cinema.setCount(count);
        CinemaDAO cinemaDAO = new CinemaDAO();
        try {
            cinemaDAO.add(cinema);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("message", "Фильм успешно создан!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }
}
