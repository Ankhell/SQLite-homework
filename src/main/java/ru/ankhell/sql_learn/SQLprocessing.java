package ru.ankhell.sql_learn;

import ru.ankhell.sql_learn.sql.SQLiteOutput;
import ru.ankhell.sql_learn.sql.SQLiteQuery;
import ru.ankhell.sql_learn.sql.SQLiteConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.HashMap;

public class SQLprocessing {
    public final static boolean DEBUG = false;
    public final static String DBURL = "../SQLite/chinook.db";
    public final static String SQL_QUERY = "SELECT * FROM tracks";

    public static void main(String[] args) throws SQLException, IOException {
        Connection DBConnection = SQLiteConnection.connect(DBURL);
        HashMap<String,Integer> namesAndWidths = SQLiteQuery.getColNamesAndWidthByQuery(DBConnection,SQL_QUERY);

        try(ResultSet results = SQLiteQuery.getQueryResults(DBConnection,SQL_QUERY);){
            SQLiteOutput.setColumnsMaxWidth(namesAndWidths,50);
//            SQLiteOutput.setColumnWidth(namesAndWidths);
            SQLiteOutput.printHeader(namesAndWidths);
            SQLiteOutput.printTableEntries(namesAndWidths,results);
        }
        catch (SQLException e){
            throw new SQLException(e);
        }
        catch (IOException e){
            throw new IOException(e);
        }

        SQLiteConnection.close(DBConnection);
    }
}
