package ru.ankhell.sql_learn.sql;

import java.sql.*;

import static ru.ankhell.sql_learn.SQLprocessing.*;

public class SQLiteConnection {
    public static Connection connect(String url) {
        Connection conn = null;
        try {
            // db parameters
            url = "jdbc:sqlite:" + url;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            if (DEBUG) System.out.println("(DEBUG) Connection to SQLiteDB has been established.");

            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void close(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
            if (DEBUG) System.out.println("(DEBUG) Connection to SQLiteDB closed.");
        }
        else System.out.println("Can't open NULL");
    }
}