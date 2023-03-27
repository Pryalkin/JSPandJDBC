package com.example.myfirstapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {

    private static Connection conn;

    static {
        String userName = "root";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost:3306/app_cinema";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cinema> getAll() throws SQLException {
        List<Cinema> cinemas = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("select * from cinema");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Cinema cinema = new Cinema();
            cinema.setId(rs.getInt(1));
            cinema.setMovieName(rs.getString(2));
            cinema.setCount(rs.getInt(3));
            cinemas.add(cinema);
        }
        return cinemas;
    }

    public void add(Cinema cinema) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into cinema (movieName, count) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, cinema.getMovieName());
        ps.setDouble(2, cinema.getCount());
        ps.execute();
    }

    public String buy(Integer count, Integer id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from cinema where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Cinema cinema = new Cinema();
        cinema.setId(rs.getInt(1));
        cinema.setMovieName(rs.getString(2));
        cinema.setCount(rs.getInt(3));
        if (cinema.getCount() < count) return "Билетов меньше запрошенного! Увы.";
        cinema.setCount(cinema.getCount() - count);
        Statement statement = conn.createStatement();
        statement.executeUpdate("UPDATE cinema SET movieName = '" + cinema.getMovieName() +
                "', count = " + cinema.getCount() + " where id = " + cinema.getId());
        return "Билеты успешно куплены!";
    }

}
