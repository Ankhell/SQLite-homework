package ru.ankhell.sql_learn.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLOutput {
    public static void printHeader(HashMap<String,Integer> namesAndWidhts){
        for (Map.Entry<String,Integer> mapEntry : namesAndWidhts.entrySet()){
            String outputFormat = "%-" + mapEntry.getValue() + "s|";
            System.out.printf(outputFormat,mapEntry.getKey());
        }
        System.out.println();
    }

    public static void printTableEntries(HashMap<String,Integer> namesAndWidhts, ResultSet table) throws SQLException {
//        ResultSetMetaData tableMeta = table.getMetaData();
        while (table.next()){
            for (Map.Entry<String,Integer> mapEntry : namesAndWidhts.entrySet()){
                String format ="%-" + mapEntry.getValue() + "s|";
                System.out.printf(format,table.getString(mapEntry.getKey()));
            }
            System.out.println();
        }
        table.close();
    }
}
