package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        System.out.println(Dish.allDishesToString());

        Cook cook = new Cook("Amigo");
        Cook cook2 = new Cook("Amigo 2");
        //StatisticManager.getInstance().register(cook);
        //StatisticManager.getInstance().register(cook2);

        Thread cookThread=new Thread(cook);
        cookThread.start();
        Thread cook2Thread=new Thread(cook2);
        cook2Thread.start();

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook2.addObserver(waiter);

        //OrderManager manager = new OrderManager();
        

        List<Tablet> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            //tablet.addObserver(cook);
            //tablet.addObserver(cook2);
            //tablet.addObserver(manager);
            list.add(tablet);
        }

        Thread thread = new Thread(new RandomOrderGeneratorTask(list, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printCookWorkloading();
        directorTablet.printArchivedVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printActiveVideoSet();

        /*
        System.out.println(Dish.allDishesToString());
        Cook cook = new Cook("Amigo");
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        tablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printCookWorkloading();
        directorTablet.printArchivedVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printActiveVideoSet();
*/


    }
}
