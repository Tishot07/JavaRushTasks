package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        String file1 = args[0];
        String file2 = args[1];
        BufferedReader reader = new BufferedReader(new FileReader(file1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
        StringBuilder stringBuilder = new StringBuilder("");
        while (reader.ready()) {
            String line = reader.readLine();
            String[] word = line.split(" ");
            for (String s : word) {
                if (s.length() > 6) {
                    stringBuilder.append(s + ",");
                }
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        writer.write(stringBuilder.toString());
        reader.close();
        writer.close();
    }
}
