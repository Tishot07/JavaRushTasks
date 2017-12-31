package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s1 = reader.readLine();
        String s2 = reader.readLine();

        FileInputStream file1 = new FileInputStream(s1);
        FileOutputStream file2 = new FileOutputStream(s2);

        byte[] buffer = new byte[file1.available()];
        int count =  buffer.length;
        file1.read(buffer);
        System.out.println(buffer);

        byte[] temp = new byte[count];
        int j = 0;
        for (int i = count-1; i >= 0;  --i) {
            temp[j] = buffer[i]; j++;
        }
        System.out.println(temp);
        file2.write(temp);
        file1.close();
        file2.close();

    }
}
