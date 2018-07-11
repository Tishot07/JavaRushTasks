package com.javarush.task.task16.task1622;

/* 
Последовательные выполнения нитей
*/

import java.rmi.server.ExportException;

import static java.lang.System.out;

public class Solution {
    public volatile static int COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < COUNT; i++) {
            new SleepingThread().join();
            //напишите тут ваш код
            //Thread.currentThread().join(); //не идет так, только для первой нити и висит
        }
    }

    public static class SleepingThread extends Thread {
        private static volatile int threadCount = 0;
        private volatile int countDownIndex = COUNT;

        public SleepingThread() {
            super(String.valueOf(++threadCount));
            start();
        }

        public void run() {
            while (true) {
                out.println(this);
                if (--countDownIndex == 0) return;
                //напишите тут ваш код
                try {
                    Thread.sleep(10);
                }
                catch (Exception e)
                {
                    System.out.println("Нить прервана");
                }
            }

        }

        public String toString() {
            return "#" + getName() + ": " + countDownIndex;
        }
    }
}
