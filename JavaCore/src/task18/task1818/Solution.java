package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        FileOutputStream out = new FileOutputStream(file1);
        FileInputStream in1 = new FileInputStream(file2);
        FileInputStream in2 = new FileInputStream(file3);

        while (in1.available() > 0)
            out.write(in1.read());
        in1.close();

        while (in2.available() > 0)
            out.write(in2.read());
        in2.close();

        out.close();
    }
}
