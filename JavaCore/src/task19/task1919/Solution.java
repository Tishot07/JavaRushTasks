package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader file = new BufferedReader(new FileReader(args[0]));//args[0]

        Map<String, Double> map = new TreeMap<>();

        while (file.ready()) {
            String[] splitLine = file.readLine().split(" ");
            if (map.containsKey(splitLine[0])) {
                map.put(splitLine[0], map.get(splitLine[0]) + Double.parseDouble(splitLine[1]));
            } else {
                map.put(splitLine[0], Double.parseDouble(splitLine[1]));
            }
        }
        file.close();

        for (Map.Entry<String, Double> item :
             map.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }


    }
}
