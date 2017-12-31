package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nameFile = reader.readLine();

        FileInputStream in = new FileInputStream(nameFile);

        int mixByte = in.read();

        while (in.available() > 0) {
            int temp = in.read();
            if (mixByte > temp)
                mixByte = temp;
        }

        System.out.println(mixByte);
        reader.close();
        in.close();
    }
}
