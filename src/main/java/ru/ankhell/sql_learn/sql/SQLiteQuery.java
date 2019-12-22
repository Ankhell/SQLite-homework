package ru.ankhell.sql_learn.sql;

import org.jetbrains.annotations.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static ru.ankhell.sql_learn.SQLprocessing.*;

public class SQLiteQuery {
    @NotNull
    public static String getAllTabQuery(String tabName) throws SQLException {
        if ((tabName == null)||(tabName.equals(""))){
            throw new SQLException("Empty Table name argument");
        }
        return "SELECT * FROM " + tabName;
    }

    public static HashMap<String, Integer> getColNamesAndWidthByQuery(Connection conn, String sqlQuery) throws SQLException {
        HashMap<String, Integer> colNamesAndWidth = new HashMap<>();
        try (Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sqlQuery)) {
            ResultSetMetaData resultSetMeta = results.getMetaData();
            int ColsCount = resultSetMeta.getColumnCount();
            for (int i = 0; i < ColsCount; i++) {
                colNamesAndWidth.put(resultSetMeta.getColumnName(i + 1), resultSetMeta.getColumnName(i + 1).length());
            }
            while (results.next()) {
                for (int i = 0; i < ColsCount; i++) {
                    String currentColumnName = resultSetMeta.getColumnName(i + 1);
                    String currentValue = results.getString(currentColumnName);
                    if (currentValue != null) {
                        if (colNamesAndWidth.get(currentColumnName) < currentValue.length()) {
                            colNamesAndWidth.put(currentColumnName, currentValue.length());
                        }
                    }
                }
            }
            if (debug) {
                System.out.println("(DEBUG) Columns widths: ");
                for (Map.Entry<String, Integer> mapEntity : colNamesAndWidth.entrySet()) {
                    System.out.println("(DEBUG) " + mapEntity.getKey() + " : " + mapEntity.getValue());
                }
            }
            System.out.println();
            return colNamesAndWidth;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static ResultSet getQueryResults(@NotNull Connection conn, String sqlQuery) throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sqlQuery);
            if (debug) System.out.println("(DEBUG) Query executed correctly returning results");
            return results;
        } catch (SQLException e) {
            if (debug) {
                System.out.println("(DEBUG) Warning! returning null!");
                System.out.println(e.getMessage());
            }
            throw e;
        }
    }
}
