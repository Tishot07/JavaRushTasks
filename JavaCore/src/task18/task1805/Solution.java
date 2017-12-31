package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nameFile = reader.readLine();

        FileInputStream in = new FileInputStream(nameFile);

        ArrayList<Integer> list = new ArrayList<>();

        while (in.available() > 0)
        {
            list.add(in.read());
        }

        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        Set<Integer> set = new LinkedHashSet<>(list);

        for (Integer listSet : set)
        {
            System.out.print(listSet + " ");
        }
        in.close();
    }
}
