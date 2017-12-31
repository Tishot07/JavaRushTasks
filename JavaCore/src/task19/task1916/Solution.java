package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = reader.readLine();
        String name2 = reader.readLine();
        reader.close();

        BufferedReader file1 = new BufferedReader(new FileReader(name1));
        BufferedReader file2 = new BufferedReader(new FileReader(name2));

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        while (file1.ready()) {
            list1.add(file1.readLine());
        }
        while (file2.ready()) {
            list2.add(file2.readLine());
        }

        file1.close();
        file2.close();

        while (list1.size() > 0 && list2.size() > 0) {
            if (list1.get(0).equals(list2.get(0))) {
                lines.add(new LineItem(Type.SAME, list1.get(0)));
                System.out.println(Type.SAME + " " + list1.get(0));
                list1.remove(0);
                list2.remove(0);
            } else if (list1.get(0).equals(list2.get(1))) {
                lines.add(new LineItem(Type.ADDED, list2.get(0)));
                System.out.println(Type.ADDED + " " + list2.get(0));
                list2.remove(0);
            } else {
                lines.add(new LineItem(Type.REMOVED, list1.get(0)));
                System.out.println(Type.REMOVED + " " + list1.get(0));
                list1.remove(0);
            }
        }
        if (list1.size() == 1) {
            lines.add(new LineItem(Type.REMOVED, list1.get(0)));
            System.out.println(Type.REMOVED + " " + list1.get(0));
            list1.remove(0);
        } else if (list2.size() == 1) {
            lines.add(new LineItem(Type.ADDED, list2.get(0)));
            System.out.println(Type.ADDED + " " + list2.get(0));
            list2.remove(0);
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
