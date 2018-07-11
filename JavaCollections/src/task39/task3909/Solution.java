package com.javarush.task.task39.task3909;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/*
Одно изменение
*/
public class Solution {

    static LinkedList<Character> firstList;
    static LinkedList<Character> secondList;

    public static void main(String[] args) {
        String str1 = "abcs";
        String str2 = "abcd";
        System.out.println(isOneEditAway(str1, str2));

        String str3 = "abc";
        String str4 = "abcd";
        System.out.println(isOneEditAway(str3, str4));

        System.out.println(isOneEditAway("abcd", "abc"));

        System.out.println("====");
        System.out.println(isOneEditAway("", "")); // true
        System.out.println(isOneEditAway("", "m")); //true
        System.out.println(isOneEditAway("m", "")); //true
        //System.out.println(isOneEditAway("m", null)); //
        System.out.println("------");
        System.out.println(isOneEditAway("mama", "ramas")); //false
        System.out.println(isOneEditAway("mamas", "rama")); //false
        System.out.println(isOneEditAway("rama", "mama")); //true
        System.out.println(isOneEditAway("mama", "dama")); //true
        System.out.println(isOneEditAway("amms", "amm"));  //true
        System.out.println(isOneEditAway("mama", "ama")); //true
        System.out.println(isOneEditAway("1234", "12367")); //false
    }

    public static boolean isOneEditAway(String first, String second) {

        //сборная солянка

        firstList = new LinkedList<Character>();
        secondList = new LinkedList<Character>();
        //листы нужны  для Collections.frequency
        for (Character character : first.toCharArray()) firstList.add(character);
        for (Character character : second.toCharArray()) secondList.add(character);

        if (first.equals(second))
            return true;
        if ( first.equals("") && second.equals(""))
            return true;

        //когда строчки равны по длине
        if (first.length() == second.length()) {
            int count = 0;
            if(first.length() == 1) return true;
            else {
                for(int i = 0; i < first.length(); i++){
                    if(first.charAt(i) != second.charAt(i)) count++;
                }
            }

            if(count == 1)
                return true;
            else
                return false;
        } else
            //когда первая строчка длиннее на 1 символ
            if (first.length() - second.length() == 1) {
                //лист позиций буквы, у которой разная частота повторений в строчках
                ArrayList<Integer> positions = new ArrayList<Integer>();
                // пробегаем и смотрим по буквам из первой строчки, где частота разная, это и есть наши кандидаты на удаление
                for (int i = 0; i < first.length(); i++) {
                    if (Collections.frequency(firstList,firstList.get(i)) != Collections.frequency(secondList,firstList.get(i)))
                        positions.add(i);
                }
                // пробегаем по листу с позициями, удаляем символ, сравниваем строчки, если не равны то вставляем символ обратно и го снова искать
                for( Integer i : positions) {
                    Character character = firstList.get(i);
                    firstList.remove(firstList.get(i));
                    if (Arrays.equals(firstList.toArray(),secondList.toArray()))
                        return true;
                    else
                        firstList.add(i,character);
                }
            } else
                //когда вторая строчка длиннее на 1 символ
                //код аналогичный
                if (second.length() - first.length() == 1) {
                    ArrayList<Integer> positions2 = new ArrayList<Integer>();

                    for (int i = 0; i < second.length(); i++) {
                        if (Collections.frequency(secondList,secondList.get(i)) != Collections.frequency(firstList,secondList.get(i)))
                            positions2.add(i);
                    }

                    for(Integer i : positions2) {
                        Character character = secondList.get(i);
                        secondList.remove(secondList.get(i));
                        if (Arrays.equals(firstList.toArray(),secondList.toArray()))
                            return true;
                        secondList.add(i,character);
                    }
                }

        return false;
    }

    /*
    public static boolean isOneEditAway(String first, String second) {
        int len1 = first.length();
        int len2 = second.length();
        int delta = Math.abs(len1 - len2);

        if (delta > 1)
            return false;

        if (first.equals("") && second.equals(""))
            return true;


        if(first.equals(second))
            return true;

        StringBuffer big = (first.length() >= second.length()) ? new StringBuffer(first) : new StringBuffer(second);
        StringBuffer temp = (first.length() < second.length()) ? new StringBuffer(first) : new StringBuffer(second);

        for (int i = 0; i < temp.length(); i++) {
            //символы не равны
            if (big.charAt(i) != temp.charAt(i))
                //если длины разные
                if (delta != 0) {
                    big.deleteCharAt(i);
                } else {
                    big.deleteCharAt(i);
                    temp.deleteCharAt(i);
                }

            break;
        }

        //если длины разные, удаляем из большего слова крайний символ
        if (big.length() != temp.length()) big.deleteCharAt(big.length()-1);

        //оставшиеся символы сраниваем
        return big.toString().equals(temp.toString());

    }
    */

}
