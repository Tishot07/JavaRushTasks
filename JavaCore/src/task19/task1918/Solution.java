package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();

        BufferedReader file = new BufferedReader(new FileReader(nameFile));

        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        while (file.ready()) {
            sb.append(file.readLine());
        }

        //записываем индексы вхождения
        int index = 0;
        while (true) {
            index = sb.indexOf("<" + args[0], index);
            if (index == -1) {
                break;
            }
            startList.add(index);
            index++;
        }

        //соответствующие закрывающиеся...
        index = 0;
        while (true) {
            index = sb.indexOf("/" + args[0], index);
            if (index == -1) {
                break;
            }
            endList.add(index);
            index++;
        }

        //выводим всё это дело в консоль
        while (!startList.isEmpty()) {
            System.out.println(sb.substring(startList.get(0), endList.get(0)) + "/" + args[0] + ">");
            startList.remove(0);
            endList.remove(0);
        }

        file.close();
    }
}
