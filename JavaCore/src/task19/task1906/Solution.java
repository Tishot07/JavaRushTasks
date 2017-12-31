package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileIn = reader.readLine();
        String fileOut = reader.readLine();
        reader.close();

        FileReader fileReader = new FileReader(fileIn);
        FileWriter fileWriter = new FileWriter(fileOut);

        int i = 0;
        while (fileReader.ready()) {
            i++;
            int b = fileReader.read();
            if (i%2 == 0) {
                fileWriter.write(b);
            }
        }

        fileReader.close();
        fileWriter.close();
    }
}
