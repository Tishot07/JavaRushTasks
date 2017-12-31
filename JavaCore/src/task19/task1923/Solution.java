package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader(args[0]);
        BufferedReader read = new BufferedReader(file);

        FileWriter file2 = new FileWriter(args[1]);

        while (read.ready()) {
            String[] str = read.readLine().split(" ");
            for (int i = 0; i < str.length; i++) {
                if (!str[i].matches("^\\D*$")) {
                    file2.write(str[i] + " ");
                }
            }

        }

        file.close();
        file2.close();

    }
}
