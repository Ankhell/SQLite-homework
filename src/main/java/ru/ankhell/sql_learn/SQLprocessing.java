package ru.ankhell.sql_learn;

import ru.ankhell.sql_learn.sql.SQLiteOutput;
import ru.ankhell.sql_learn.sql.SQLiteQuery;
import ru.ankhell.sql_learn.sql.SQLiteConnection;
import ru.ankhell.sql_learn.io.Input;
import ru.ankhell.sql_learn.config.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SQLprocessing {
    private final static boolean USE_CFG = false;
    private final static String CONFIG_NAME = "config.cfg";
    public static boolean debug = true;

    public static void main(String[] args) throws SQLException {
        int maxColWidth;
        String tabName;
        String db_url = "src/main/resources/chinook.db";
        String sqlQuery;

        try{
            if (USE_CFG){
                Config cfg = new Config(CONFIG_NAME);
                debug = Boolean.parseBoolean(cfg.getProperty("DEBUG"));
                db_url = cfg.getProperty("URL");
                tabName = cfg.getProperty("TABLE");
                maxColWidth = Integer.parseInt(cfg.getProperty("MAX_WIDTH"));
            } else {
                tabName = Input.getString("Please enter table name: ");
                maxColWidth = Integer.parseInt(Input.getString("Please enter max column width: "));
            }
        }
        catch (Exception e){
            System.out.println("Loading default config");
            tabName = Input.getString("Please enter table name: ");
            maxColWidth = Integer.parseInt(Input.getString("Please enter max column width: "));
        }



        sqlQuery = SQLiteQuery.getAllTabQuery(tabName);

        Connection DBConnection = SQLiteConnection.connect(db_url);
        HashMap<String, Integer> namesAndWidths = SQLiteQuery.getColNamesAndWidthByQuery(DBConnection, sqlQuery);
        ResultSet results = SQLiteQuery.getQueryResults(DBConnection, sqlQuery);

        SQLiteOutput.setColumnsMaxWidth(namesAndWidths, maxColWidth);
        System.out.printf("|#%s|%n", tabName);
        SQLiteOutput.printHeader(namesAndWidths);
        SQLiteOutput.printTableEntries(namesAndWidths, results);

        results.close();
        SQLiteConnection.close(DBConnection);

    }
}
