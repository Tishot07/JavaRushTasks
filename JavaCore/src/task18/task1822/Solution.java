package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();
        BufferedReader data = new BufferedReader(new FileReader(nameFile));
        String str;

        while ((str = data.readLine()) != null) {
            if (str.startsWith(args[0])) {
                System.out.println(str);
                break;
            }
        }
        data.close();


    }
}
