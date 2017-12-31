package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null)
            throw new TooShortStringException();

        if (string.matches(".*(.*\\s){4}.*")) {
            String part = string.replaceAll("^\\S*\\s(\\S*\\s\\S*\\s\\S*\\s\\S*).*$", "$1");
            return part;
        } else throw new TooShortStringException();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
