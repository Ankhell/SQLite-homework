package ru.ankhell.sql_learn.sql;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static ru.ankhell.sql_learn.SQLprocessing.*;

public class SQLiteQuery {
    public static HashMap<String, Integer> getColNamesAndWidthByQuery(Connection conn, String sqlQuery) {
        HashMap<String, Integer> colNamesAndWidth = new HashMap<>();
        try (Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sqlQuery)) {
            ResultSetMetaData resultSetMeta = results.getMetaData();
            for (int i = 0; i < resultSetMeta.getColumnCount(); i++) {
                colNamesAndWidth.put(resultSetMeta.getColumnName(i + 1), resultSetMeta.getColumnName(i + 1).length());
            }
            while (results.next()) {
                for (int i = 0; i < resultSetMeta.getColumnCount(); i++) {
                    if (results.getString(resultSetMeta.getColumnName(i + 1)) != null) {
                        if (colNamesAndWidth.get(resultSetMeta.getColumnName(i + 1)) <
                                results.getString(resultSetMeta.getColumnName(i + 1)).length()) {
                            colNamesAndWidth.put(resultSetMeta.getColumnName(i + 1),
                                    results.getString(resultSetMeta.getColumnName(i + 1)).length());
                        }
                    }
                }
            }
            if (DEBUG) {
                for (Map.Entry<String, Integer> mapEntity : colNamesAndWidth.entrySet()) {
                    System.out.println("(DEBUG) " + mapEntity.getKey() + " : " + mapEntity.getValue());
                }
            }
            System.out.println();
            return colNamesAndWidth;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static HashMap<String, Integer> getAllColNamesAndWidthByTabName(Connection conn, String tabName) {
        return getColNamesAndWidthByQuery(conn, "SELECT * FROM " + tabName);
    }

    public static ResultSet getQueryResults(Connection conn, String sqlQuery) throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sqlQuery);
            if (DEBUG) System.out.println("(DEBUG) Query executed correctly returning results");
            return results;
        } catch (SQLException e) {
            if (DEBUG) {
                System.out.println("(DEBUG) Warning! returning null!");
                System.out.println(e.getMessage());
            } else throw new SQLException(e);
        }
        return null;
    }
}
