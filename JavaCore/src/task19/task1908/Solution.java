package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = r.readLine();
        String outputFileName = r.readLine();
        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(inputFileName));
        BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(outputFileName));

        String output = bufferedFileReader.readLine();
        String output2 = "";

        while (output != null){
            output2 += output + " ";
            output = bufferedFileReader.readLine();
        }

        String[] list = output2.split(" ");
        for (String x : list){
            try {
                int i = Integer.parseInt(x);
                System.out.println(i);
                bufferedFileWriter.write(i + " ");
            } catch (NumberFormatException e) {
                continue;
            }
        }
        r.close();
        bufferedFileReader.close();
        bufferedFileWriter.close();

    }
}
