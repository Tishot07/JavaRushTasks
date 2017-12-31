package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {

        FileReader fileRead = new FileReader(args[0]);
        BufferedReader read = new BufferedReader(fileRead);

        while (read.ready()) {
            String line = read.readLine();
            String[] splittedLine = line.split(" ");
            int year = Integer.parseInt(splittedLine[splittedLine.length- 1]);
            int month = Integer.parseInt(splittedLine[splittedLine.length- 2]);
            int day = Integer.parseInt(splittedLine[splittedLine.length- 3]);
            String name = "";
            for (int i = 0; i < splittedLine.length - 3; i++) name = name + splittedLine[i] + " ";
            PEOPLE.add(new Person(name.trim(), new Date(year-1900,month-1,day)));
        }
        fileRead.close();

    }
}
