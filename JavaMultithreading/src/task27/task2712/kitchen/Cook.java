package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

//public class Cook extends Observable implements Observer {
public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /*
    @Override
    public void update(Observable o, Object arg) {
        //- observable - объект, который отправил нам значение
        //- arg - само значение, в нашем случае - это объект Order
        ConsoleHelper.writeMessage("Start cooking - " + arg);
        Order order = (Order) arg;
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime(), order.getDishes()));
        setChanged();
        notifyObservers(arg);


    }
    */
    public void startCookingOrder(Order order) {
        this.busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        //Order order = (Order) arg;
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime(), order.getDishes()));
        setChanged();
        notifyObservers(order);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
        }
        this.busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!busy) {
            try {
                startCookingOrder(queue.take());
            } catch (InterruptedException e) {
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
