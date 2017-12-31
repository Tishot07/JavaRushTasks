package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();

        FileReader read = new FileReader(nameFile);

        StringBuilder buf = new StringBuilder();
        while(read.ready()){
            buf.append((char)read.read());
        }
        read.close();

        String str = buf.toString();
        int i = str.split("\\bworld\\b").length - 1;

        System.out.println(i);
    }
}
