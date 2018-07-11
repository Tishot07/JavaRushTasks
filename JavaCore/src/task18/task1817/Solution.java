package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream(args[0]);

        int ch32 = 0;
        int result = 0;
        while (file.available() >0) {
            int temp = file.read();
            result++;
            if (temp == 32) ch32++;
        }
        file.close();
        System.out.println(String.format("%.2f", (double)ch32/result*100));
    }
}
