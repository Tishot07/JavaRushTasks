package com.javarush.task.task30.task3004;


import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int i;

    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        int a = i % 2;
        int b = i / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask bin = new BinaryRepresentationTask(b);
            bin.fork();
            return bin.join() + result;
        }
        return result;
    }
}
