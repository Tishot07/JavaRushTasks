package com.javarush.task.task16.task1603;

import java.util.ArrayList;
import java.util.List;

/* 
Список и нити
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {
        //Add your code here - добавьте свой код тут
        SpecialThread s1 = new SpecialThread();
        Thread t1 = new Thread(s1);
        list.add(t1);
        SpecialThread s2 = new SpecialThread();
        Thread t2 = new Thread(s2);
        list.add(t2);
        SpecialThread s3 = new SpecialThread();
        Thread t3 = new Thread(s3);
        list.add(t3);
        SpecialThread s4 = new SpecialThread();
        Thread t4 = new Thread(s4);
        list.add(t4);
        SpecialThread s5 = new SpecialThread();
        Thread t5 = new Thread(s5);
        list.add(t5);
    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's a run method inside SpecialThread");
        }
    }
}
