package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new thread1());
        threads.add(new thread2());
        threads.add(new thread3());
        threads.add(new thread4());
        threads.add(new thread5());
    }

    public static class thread1 extends Thread {

        public void run() {
            while (true)
            {

            }
        }
    }


    public static class thread2 extends Thread {

        public void run() {
            try {
                join();
            } catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }


    public static class thread3 extends Thread {

        public void run() {
            while (true) {
                System.out.println("Ура");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }


    public static class thread4 extends Thread implements Message {

        @Override
        public void showWarning() {
            this.interrupt();

        }

        public void run() {
            super.run();
        }
    }


    public static class thread5 extends Thread {

        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int summ = 0;
            while (!isInterrupted()) {
                String str;
                try {
                    while (!"N".equals(str = reader.readLine())) {
                        summ += Integer.parseInt(str);
                    }
                    System.out.println(summ);
                } catch (IOException e) {
                }

            }

        }
    }

    public static void main(String[] args) {

    }
}