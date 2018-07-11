package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name1 = reader.readLine();
        String name2 = reader.readLine();

        FileInputStream file1 = new FileInputStream(name1);
        byte[] temp = new byte[file1.available()];
        while (file1.available()>0)
            file1.read(temp);
        file1.close();

        FileOutputStream file2 = new FileOutputStream(name1);
        FileInputStream file3 = new FileInputStream(name2);

        while (file3.available() > 0)
            file2.write(file3.read());
        file3.close();
        file2.write(temp);
        file2.close();

    }
}
