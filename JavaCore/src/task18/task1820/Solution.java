package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        BufferedReader f1 = new BufferedReader(new FileReader(file1));
        BufferedWriter f2 = new BufferedWriter(new FileWriter(file2));
        StringBuilder data = new StringBuilder("");
        while (f1.ready()) {
            data.append(f1.readLine() + " ");
        }
        Scanner in = new Scanner(data.toString());
        while (true) {
            if (!in.hasNextDouble())
                break;
            f2.write((int)Math.round(in.nextDouble()) + " ");
        }
        in.close(); f1.close(); f2.close();
    }
}
