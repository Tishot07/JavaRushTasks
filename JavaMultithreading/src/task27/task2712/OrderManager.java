/*
package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    //private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread thread = new Thread(new Runnable() {
            StatisticManager manager = StatisticManager.getInstance();
            @Override
            public void run() {
                while (true) {
                    if (!orderQueue.isEmpty()) {
                        Set<Cook> list = manager.getCooks();
                        for (Cook temp :
                                list) {
                            if (!temp.isBusy()) {
                                temp.startCookingOrder(orderQueue.poll());
                            }
                        }
                    } else {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        orderQueue.add(order);
    }
}
*/
