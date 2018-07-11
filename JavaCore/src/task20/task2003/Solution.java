package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String nameFIle = read.readLine();
        FileInputStream file = new FileInputStream(nameFIle);
        load(file);
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties property = new Properties();
        for (Map.Entry<String, String> map:
             properties.entrySet()) {
            property.put(map.getKey(), map.getValue());
        }
        property.store(outputStream, null);

    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties pro = new Properties();
        pro.load(inputStream);
        properties.clear();
        properties.putAll((Map)pro);
    }

    public static void main(String[] args) {

    }
}
