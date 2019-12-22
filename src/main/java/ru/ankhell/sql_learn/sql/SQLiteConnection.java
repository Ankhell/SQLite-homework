package ru.ankhell.sql_learn.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.ankhell.sql_learn.SQLprocessing.*;

public class SQLiteConnection {
    public static Connection connect(String url) throws SQLException {
        Connection conn;
        url = "jdbc:sqlite:" + url;
        conn = DriverManager.getConnection(url);

        if (debug) System.out.println("(DEBUG) Connection to SQLiteDB has been established.");

        return conn;
    }

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
            if (debug) System.out.println("(DEBUG) Connection to SQLiteDB closed.");
        } else {
            System.out.println("No connection closed! argument is null");
        }
    }
}