package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<FileInputStream> list = new ArrayList<>();
        String buf = "";
        while (true) {
            try {
                buf = reader.readLine();
                list.add(new FileInputStream(buf));
            } catch (FileNotFoundException e) {
                System.out.println(buf);
                for (FileInputStream fileClose:
                     list) {
                    fileClose.close();
                }
                return;
            }
        }

    }
}
