package ru.ankhell.sql_learn.sql;

import org.jetbrains.annotations.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import ru.ankhell.sql_learn.io.Input;

public class SQLiteOutput {

    public static void printHeader(@NotNull HashMap<String, Integer> namesAndWidths) {
        System.out.print("|");
        for (Map.Entry<String, Integer> mapEntry : namesAndWidths.entrySet()) {
            String outputFormat = "%-" + mapEntry.getValue() + "s|";
            System.out.printf(outputFormat, mapEntry.getKey());
        }
        System.out.println();
    }

    public static void printTableEntries(@NotNull HashMap<String, Integer> namesAndWidths, @NotNull ResultSet table)
            throws SQLException {
        int i = 0;
        while (table.next()) {
            i++;
            System.out.print("|");
            for (Map.Entry<String, Integer> mapEntry : namesAndWidths.entrySet()) {
                String value = "";
                String colName = mapEntry.getKey();
                String entryValue = table.getString(colName);
                int maxColWidth = mapEntry.getValue();

                if (entryValue != null) {
                    value = entryValue.length() > maxColWidth ? entryValue.substring(0, maxColWidth) : entryValue;
                }
                String format = "%-" + mapEntry.getValue() + "s|";
                System.out.printf(format, value);
            }
            if (i == 1000) {
                System.out.println("\nPress any key to continue");
                try {
                    //noinspection ResultOfMethodCallIgnored
                    System.in.read();
                } catch (IOException err) {
                    System.out.println("An error occurred, but it doesn't matter");
                }
                printHeader(namesAndWidths);
                i = 0;
            }
            System.out.println();
        }
    }

// --Commented out by Inspection START (22.12.2019 21:02):
//    public static void setColumnWidth(@NotNull HashMap<String, Integer> namesAndWidths) {
//        for (Map.Entry<String, Integer> mapEntry : namesAndWidths.entrySet()) {
//            String outputString = "/nPlease define width for column " +
//                    mapEntry.getKey() + "(current width " + mapEntry.getValue() + ")";
//            mapEntry.setValue(Math.max((Integer.parseInt(Input.getString(outputString))), mapEntry.getKey().length()));
//        }
//    }
// --Commented out by Inspection STOP (22.12.2019 21:02)

    public static void setColumnsMaxWidth(@NotNull HashMap<String, Integer> namesAndWidths, int maxWidth) {
        for (Map.Entry<String, Integer> mapEntry : namesAndWidths.entrySet()) {
            mapEntry.setValue(Math.min(maxWidth, mapEntry.getValue()));
        }
    }

}
