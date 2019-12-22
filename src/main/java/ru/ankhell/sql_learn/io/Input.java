package ru.ankhell.sql_learn.io;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    @NotNull
    public static String getString(String message) {
        System.out.print(message);
        BufferedReader ibs = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try{
                String inLine = ibs.readLine();
                if (inLine.equals("")){
                    throw new IOException("Input can't be blank");
                }
                return inLine;
            }
            catch (IOException err){
                System.out.println(err.getMessage()+ " пожалуйста повторите ввод. ");
            }
        }
    }
}
