package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String fileName = read.readLine();
        read.close();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fileReader);

        List<String[]> lines = new ArrayList<>();
        while (reader.ready()) {
            lines.add(reader.readLine().split(" "));
        }

        for (String[] str:
             lines) {
            int count = 0;
            for (int i = 0; i < str.length; i++) {
                if (words.contains(str[i])) {
                    count++;
                }
            }
            if (count == 2) {
                for (int i = 0; i < str.length; i++) {
                    System.out.print(str[i] + " ");
                }
                System.out.println();
            }
        }

        fileReader.close();
    }
}
