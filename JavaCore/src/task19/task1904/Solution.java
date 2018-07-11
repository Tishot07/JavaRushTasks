package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String s = fileScanner.nextLine();
            String[] buf = s.split(" ");
            String dateSub = s.substring(s.indexOf(' ',s.indexOf(' ',s.indexOf(' ')+1)+1)+1);
            Date date = new Date(dateSub);
            return new Person(buf[1], buf[2], buf[0], date);

        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
