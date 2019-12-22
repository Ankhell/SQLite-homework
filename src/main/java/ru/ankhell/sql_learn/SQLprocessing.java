package ru.ankhell.sql_learn;

import ru.ankhell.sql_learn.sql.SQLOutput;
import ru.ankhell.sql_learn.sql.SQLQuery;
import ru.ankhell.sql_learn.sql.SQLiteConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLprocessing {
    public final static boolean DEBUG = false;
    public final static String DBURL = "../SQLite/chinook.db";

    public static void main(String[] args) throws SQLException {
        Connection DBConnection = SQLiteConnection.connect(DBURL);
//        SQLQuery.getColNamesAndWidthByQuery(DBConnection,"SELECT * FROM albums");
//        SQLQuery.getAllColNamesAndWidthByTabName(DBConnection,"albums");
        SQLOutput.printHeader(SQLQuery.getAllColNamesAndWidthByTabName(DBConnection,"albums"));
        ResultSet results = SQLQuery.getQueryResults(DBConnection,"SELECT * FROM albums");
        SQLOutput.printTableEntries(SQLQuery.getAllColNamesAndWidthByTabName(DBConnection,"albums"),results);
        SQLiteConnection.close(DBConnection);
    }
}
