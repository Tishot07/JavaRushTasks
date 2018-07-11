package com.javarush.task.task25.task2506;


public class LoggingStateThread extends Thread {
    private Thread curThread;
    LoggingStateThread(Thread t) {
        curThread = t;

    }

    @Override
    public void run() {
        Thread.State currentState = curThread.getState();
        System.out.println(currentState);
        super.run();
        while (!currentState.equals(State.TERMINATED)) {
            Thread.State newState = curThread.getState();
            if (!newState.equals(currentState)) {
                System.out.println(newState);
                currentState = newState;
            }
        }
        this.interrupt();
    }
}
