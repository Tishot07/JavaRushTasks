package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nameFile = reader.readLine();

        FileInputStream in = new FileInputStream(nameFile);

        int maxByte = 0;

        while (in.available() > 0) {
            int temp = in.read();
            if (maxByte < temp)
                maxByte = temp;
        }

        System.out.println(maxByte);
        reader.close();
        in.close();
    }
}
