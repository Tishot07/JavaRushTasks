package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream inputFile = new FileInputStream(args[1]);
        FileOutputStream outFile = new FileOutputStream(args[2]);

        if (args[0].equals("-e")) {
            //зашифровать [fileName] и записать в [fileOutputName]

            while (inputFile.available() > 0) {
                outFile.write(inputFile.read()+10);
            }
        } else if (args[0].equals("-d")) {
            while (inputFile.available() > 0) {
                outFile.write(inputFile.read()-10);
            }
        }

        inputFile.close();
        outFile.close();

    }

}
