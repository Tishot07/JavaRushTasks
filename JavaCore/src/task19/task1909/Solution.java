package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = r.readLine();
        String outputFileName = r.readLine();
        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(inputFileName));
        BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(outputFileName));

        while (bufferedFileReader.ready()) {
            int buf = bufferedFileReader.read();
            if (buf == 46)          //.
                buf = 33;           //!
            bufferedFileWriter.write(buf);
        }


        r.close();
        bufferedFileReader.close();
        bufferedFileWriter.close();

    }
}
