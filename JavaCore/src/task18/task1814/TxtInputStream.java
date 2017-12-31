package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {

    public TxtInputStream(String fileName) throws IOException, UnsupportedFileNameException {
        super(fileName);
        if (!fileName.regionMatches(fileName.length()-4, ".txt", 0, 4)) {
            super.close();
            throw new UnsupportedFileNameException();

        }


    }

    public static void main(String[] args) {
    }
}
