
package com.javarush.task.task18.task1825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        TreeSet<String> nameFiles = new TreeSet<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String buf = reader.readLine();
        //куда пишем
        String outNameFile = buf.substring(0, buf.lastIndexOf(".part"));
        while (!buf.equals("end")) {
            nameFiles.add(buf);
            buf = reader.readLine();
        }

        //дерево для длинны имен файлов (сколько всего файлов)
        TreeSet<Integer> lenghtNameFile = new TreeSet<>();
        for (String l:
                nameFiles) {
            if (!lenghtNameFile.contains(l.length())) {
                //получаем дерево из длинны имен файлов
                lenghtNameFile.add(l.length());
            }
        }

        //Массив деревьев для каждой длинны по десяткам
        TreeSet<String>[] arrayTreeSetNameFile = new TreeSet[lenghtNameFile.size()];
        for (int i = 0; i < lenghtNameFile.size(); i++) {
            arrayTreeSetNameFile[i] = new TreeSet<>();
        }

        //Для каждой длинны (индекс в массиве) добавляем имена файлов. Получаем массив, где хранятся имена файлов с одинаковой длинной
        int x = 0;
        for (Integer i:
                lenghtNameFile) {
            for (String s:
                    nameFiles) {
                if (s.length() == i) {
                    arrayTreeSetNameFile[x].add(s);
                }
            }
            x++;
        }

        //Все записываем
        FileOutputStream fileOutputStream = new FileOutputStream(outNameFile);

        for (int i = 0; i < arrayTreeSetNameFile.length; i++) {
            for (String s:
                    arrayTreeSetNameFile[i]) {
                FileInputStream bufFile = new FileInputStream(s);
                byte[] bufferIn = new byte[bufFile.available()];
                bufFile.read(bufferIn);
                fileOutputStream.write(bufferIn);
            }
        }
    }
}