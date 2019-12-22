package ru.ankhell.sql_learn.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLiteOutput {
    public static void printHeader(HashMap<String, Integer> namesAndWidhts) {
        for (Map.Entry<String, Integer> mapEntry : namesAndWidhts.entrySet()) {
            String outputFormat = "%-" + mapEntry.getValue() + "s|";
            System.out.printf(outputFormat, mapEntry.getKey());
        }
        System.out.println();
    }

    public static void printTableEntries(HashMap<String, Integer> namesAndWidhts, ResultSet table) throws SQLException, IOException {
//        ResultSetMetaData tableMeta = table.getMetaData();
        int i = 0;
        while (table.next()) {
            i++;
            for (Map.Entry<String, Integer> mapEntry : namesAndWidhts.entrySet()) {
                String value ="";
                if (table.getString(mapEntry.getKey()) != null) {
                    value = table.getString(mapEntry.getKey()).length() > mapEntry.getValue() ?
                            table.getString(mapEntry.getKey()).substring(0, mapEntry.getValue()) : table.getString(mapEntry.getKey());
                }
                String format = "%-" + mapEntry.getValue() + "s|";
                System.out.printf(format, value);
            }
            if (i == 1000) {
                System.out.println("\nPress any key to continue");
                System.in.read();
                printHeader(namesAndWidhts);
                i = 0;
            }
            System.out.println();
        }
    }

    public static void setColumnWidth(HashMap<String, Integer> namesAndWidhts) throws IOException{
        BufferedReader ibs = new BufferedReader(new InputStreamReader(System.in));
        for (Map.Entry<String,Integer> mapEntry : namesAndWidhts.entrySet()){
            System.out.printf("%nPlease define width for column %s(current width %d): ",mapEntry.getKey(),mapEntry.getValue());
            mapEntry.setValue(Math.max((Integer.parseInt(ibs.readLine())),mapEntry.getKey().length()));
        }
    }

    public static void setColumnsMaxWidth(HashMap<String, Integer> namesAndWidhts, int maxWidth){
        for (Map.Entry<String,Integer> mapEntry : namesAndWidhts.entrySet()){
            mapEntry.setValue(Math.min(maxWidth,mapEntry.getValue()));
        }
    }

}
