package com.javarush.task.task19.task1910;

/* 
Пунктуация
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
            if ((buf >= 65  && buf <= 90) || (buf >= 97  && buf <= 122) || (buf >= 192  && buf <= 255) || buf == 32)
                bufferedFileWriter.write(buf);
        }
//s = s.replaceAll("\\W","");
        r.close();
        bufferedFileReader.close();
        bufferedFileWriter.close();
    }
}
