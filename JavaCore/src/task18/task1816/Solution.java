package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream(args[0]);
        int abc = 0;
        while(file.available() > 0) {
            int temp = file.read();
            if ((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122))
                {abc++;}

        }
        file.close();
        System.out.println(abc);
    }
}
