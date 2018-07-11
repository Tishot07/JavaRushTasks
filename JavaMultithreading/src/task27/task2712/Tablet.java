package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

//public class Tablet extends Observable {
public class Tablet {
    public static Logger logger = Logger.getLogger(Tablet.class.getName());

    final int number;
    private LinkedBlockingQueue queue = new LinkedBlockingQueue();
    
    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {

        try {
            Order order = new Order(this);
            if (!order.isEmpty()) {
                //setChanged();
                //notifyObservers(order);
                queue.offer(order);
                try {
                    new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();
                }  catch (NoVideoAvailableException e) {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                }

                return order;
            } else
                return null;

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", this.number);
    }

    public void createTestOrder() {
        try {
            Order order = new TestOrder(this);
            if (!order.isEmpty()) {
                //setChanged();
                //notifyObservers(order);
                queue.offer(order);
                try {
                    new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();
                }  catch (NoVideoAvailableException e) {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                }

                //return order;
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            //return null;
        }
    }
  /*
    private void orderMethod() {

    }
    */

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }
}
