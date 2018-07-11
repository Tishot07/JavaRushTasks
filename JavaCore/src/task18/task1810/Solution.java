package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();
        FileInputStream fis = new FileInputStream(str);

        while (fis.available() > 999) {
            fis.close();
            str = reader.readLine();
            fis = new FileInputStream(str);
        }
        fis.close();
        reader.close();
        throw new DownloadException();
    }

    public static class DownloadException extends Exception {

    }
}
