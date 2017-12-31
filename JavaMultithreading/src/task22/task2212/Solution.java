package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {

        if ((telNumber == null) || (telNumber.isEmpty())) return false;

        if (telNumber.matches("^\\+.*")) {
            int count = 0;
            char[] temp = telNumber.toCharArray();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] >= '0' && temp[i] <= '9')
                    count++;
            }
            if (count != 12) return false;
        } else if (telNumber.matches("^((\\d)|(\\()).*")) {
            int count = 0;
            char[] temp = telNumber.toCharArray();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] >= '0' && temp[i] <= '9')
                    count++;
            }
            if (count != 10) return false;
        }

        if (telNumber.matches("(^\\+\\d+(\\(\\d{3}\\))?\\d+(-)?\\d+(-)?\\d+)|(^\\+\\d+)|(\\d+(-)?\\d+)"))
            return true;

        else return false;
    }




            //Pattern p = Pattern.compile("^((\\+)|(\\d)|(\\()).*");
        //Pattern p = Pattern.compile("((\\+)|(\\d)|(\\())(-){0,2}.*\\d$");
        //Matcher m = p.matcher(telNumber);
        //return m.matches();
        //if (telNumber.matches("^\\\\+\\d{12}")) return true;
        //return  (telNumber.matches(".+[0-9]$"));
        //return  (telNumber.matches("(^\\+\\d{12})|()"));
        //return  (telNumber.matches("^\\d"));
        //else return false;


    public static void main(String[] args) {
        System.out.println("1: " + checkTelNumber("+380501234567"));
        System.out.println("2: " + checkTelNumber("+38(050)1234567"));
        System.out.println("3: " + checkTelNumber("+38050123-45-67"));
        System.out.println("4: " + checkTelNumber("050123-4567"));
        System.out.println("5: " + checkTelNumber("+38)050(1234567"));
        System.out.println("6: " + checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println("7: " + checkTelNumber("050ххх4567"));
        System.out.println("8: " + checkTelNumber("050123456"));
        System.out.println("9: " + checkTelNumber("(0)501234567"));

    }
}
