package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo
Уникальные подстроки
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        ArrayList<Integer> lenghtList = new ArrayList<>();


        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(i, s.length());

            StringBuffer noRepeatLattersString = new StringBuffer();
            noRepeatLattersString.append(substring.charAt(0));
            exit:
            for (int k = 1; k < substring.length(); k++) {
                for (int j = 0; j < noRepeatLattersString.length(); j++)
                    if (noRepeatLattersString.charAt(j) == substring.charAt(k))
                        break exit;

                noRepeatLattersString.append(substring.charAt(k));
            }
            lenghtList.add(noRepeatLattersString.toString().length());
        }

        return Collections.max(lenghtList);
    }
}
