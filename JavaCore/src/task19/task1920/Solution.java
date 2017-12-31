package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

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

        Double maxVal = Collections.max(map.values());

        for (Map.Entry<String, Double> item :
                map.entrySet()) {
            if (maxVal.equals(item.getValue()))
                System.out.println(item.getKey());
        }

    }
}
