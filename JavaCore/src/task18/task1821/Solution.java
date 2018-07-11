package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.util.Map;
import java.util.TreeMap;


public class Solution {
    public static void main(String[] args) throws Exception {

        TreeMap<Integer, Integer> map = new TreeMap();
        FileInputStream inputStream = new FileInputStream(args[0]);
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            if (map.containsKey(data))
                map.replace(data, map.get(data), map.get(data) + 1);
            else map.put(data, 1);
        }
        inputStream.close();
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            int num = i.getKey();
            System.out.print((char)num + " "); System.out.println(i.getValue());
        }
    }
}
