package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
        while (!isValuePresent)
            try {
                this.wait();
            } catch (Exception e) {}
        System.out.println("Got: " + value);
        isValuePresent = false;
        try {return value;}
        finally {
            notifyAll();
        }
    }

    public synchronized void put(int value) {
        while (isValuePresent) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
        this.value = value;
        isValuePresent = true;
        this.notifyAll();
        System.out.println("Put: " + value);
    }
}
