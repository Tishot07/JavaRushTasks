package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = read.readLine();
        read.close();

        FileReader fileRead = new FileReader(nameFile);
        BufferedReader readFile = new BufferedReader(fileRead);

        while (readFile.ready()) {
            StringBuffer str = new StringBuffer();
            str.append(readFile.readLine());
            System.out.println(str.reverse());
        }
        fileRead.close();
    }
}
