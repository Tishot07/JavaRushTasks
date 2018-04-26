package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
    private static TreeMap<Integer,Character> alphabet = new TreeMap<>();

    static {
        alphabet.put(10,'A');
        alphabet.put(11,'B');
        alphabet.put(12,'C');
        alphabet.put(13,'D');
        alphabet.put(14,'E');
        alphabet.put(15,'F');
        alphabet.put(16,'G');
        alphabet.put(17,'H');
        alphabet.put(18,'I');
        alphabet.put(19,'J');
        alphabet.put(20,'K');
        alphabet.put(21,'L');
        alphabet.put(22,'M');
        alphabet.put(23,'N');
        alphabet.put(24,'O');
        alphabet.put(25,'P');
        alphabet.put(26,'Q');
        alphabet.put(27,'R');
        alphabet.put(28,'S');
        alphabet.put(29,'T');
        alphabet.put(30,'U');
        alphabet.put(31,'V');
        alphabet.put(32,'W');
        alphabet.put(33,'X');
        alphabet.put(34,'Y');
        alphabet.put(35,'Z');
    }

    public static void main(String[] args) {

        try {
            String s = args[0];
            char[] c = s.toCharArray();
            for (char ch : c)
            {
                if (!Character.isLetterOrDigit(ch)) {
                    System.out.println("incorrect");
                    return;
                }
            }

            boolean hasChar = false;
            for (char ch : c)
            {
                if(Character.isLetter(ch)) {
                    hasChar = true;
                    break;
                }
            }

            int minSys = 0;
            if(!hasChar)
            {
                for (int i = 9; i > 0; i--) {
                    if(s.contains(String.valueOf(i)))
                    {
                        minSys = i+1;
                        break;
                    }
                }

                if (minSys == 0) {
                    minSys = 2;
                }

                System.out.println(minSys);
            }
            else {

                SortedMap<Integer,Character> sortedMap = new TreeMap<>(new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }

                });

                sortedMap.putAll(alphabet);

                for(Map.Entry<Integer,Character> pair : sortedMap.entrySet())
                {
                    if(s.contains(String.valueOf(pair.getValue())) || s.contains(String.valueOf(pair.getValue()).toLowerCase())) {
                        minSys = pair.getKey() + 1;
                        break;
                    }
                }

                System.out.println(minSys);
            }
        } catch (Exception e) {

        }

    }
}