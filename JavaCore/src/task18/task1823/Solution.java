package com.javarush.task.task18.task1823;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        ArrayList<ReadThread> thread = new ArrayList<>();
        int index = 0;
        try {
            while (true)   {
                str = reader.readLine();
                if (!str.equals("exit")) {
                    thread.add(new ReadThread(str));
                    thread.get(index).start();
                    index++;
                } else return;
            }
        } catch (Exception e) {}


    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run() {
            try {
                FileInputStream file = new FileInputStream(fileName);
                HashMap<Integer, Integer> max = new HashMap<>();

                while (file.available() > 0)
                {
                    int data  = file.read();

                    if (max.containsKey(data))
                    {
                        for (HashMap.Entry<Integer, Integer> me : max.entrySet())
                        {
                            if (me.getKey().equals(data))
                            {
                                me.setValue(me.getValue() + 1);
                            }
                        }
                    }
                    else {
                        max.put(data,1);
                    }
                }

                int count=0;
                int maxByte = 0;

                for (HashMap.Entry<Integer, Integer> me : max.entrySet())
                {
                    if(count < me.getValue()) {
                        count = me.getValue();
                        maxByte = me.getKey();
                    }
                }
                file.close();
                synchronized (this) {resultMap.put(fileName, maxByte);}
            } catch (Exception e) {}

        }
    }
}
