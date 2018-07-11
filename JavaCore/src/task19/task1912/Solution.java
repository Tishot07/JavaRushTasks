package com.javarush.task.task19.task1912;

/* 
Ридер обертка 2
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        //запоминаем настоящий PrintStream в специальную переменную
        PrintStream consoleStream = System.out;

        //Создаем динамический массив
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);

        //Вызываем функцию, которая ничего не знает о наших манипуляциях
        testString.printSomething();

        //Преобразовываем записанные в наш ByteArray данные в строку
        String result = outputStream.toString();

        char[] array = result.toCharArray();



        for (int i = 0; i < array.length; i++) {
            if (array[i] == 't' && array[i+1] == 'e') {
                array[i] = '?';
                array[i + 1] = '?';
            }
        }
        System.setOut(consoleStream);
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]);
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
