package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nameFile = reader.readLine();

        FileInputStream in = new FileInputStream(nameFile);

        HashMap<Integer, Integer> min = new HashMap<>();

        int count = 0;

        while (in.available() > 0)
        {
            int data  = in.read();
            count = data;

            if (min.containsKey(data))
            {
                for (HashMap.Entry<Integer, Integer> me : min.entrySet())
                {
                    if (me.getKey().equals(data))
                    {
                        me.setValue(me.getValue() + 1);
                    }
                }
            }
            else {
                min.put(data,1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (HashMap.Entry<Integer, Integer> me : min.entrySet())
        {
            if(count > me.getValue())
            {
                count = me.getValue();
                list.clear();
                list.add(me.getKey());
            }
            else if(count==me.getValue())
            {
                list.add(me.getKey());
            }
        }
        for (Integer aList : list)
        {
            System.out.print(aList + " ");
        }
        in.close();
    }
}
