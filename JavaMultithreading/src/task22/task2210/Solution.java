package com.javarush.task.task22.task2210;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        String[] ar = getTokens("level22.lesson13.task01", ".");
        for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);
        }
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer token = new StringTokenizer(query, delimiter);
        ArrayList<String> list = new ArrayList<>();
        while (token.hasMoreTokens())
        {
            list.add(token.nextToken());
        }

        return  list.toArray(new String[list.size()]);
    }
}
