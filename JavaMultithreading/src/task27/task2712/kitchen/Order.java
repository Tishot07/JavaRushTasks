package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        //dishes = ConsoleHelper.getAllDishesForOrder();
        initDishes();
        //System.out.println(this);
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : String.format("Your order: %s of %s, cooking time %dmin", dishes, tablet, getTotalCookingTime());
    }

    public int getTotalCookingTime() {
        int total = 0;
        for (Dish d:
             dishes) {
            total += d.getDuration();
        }
        return total;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }


    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() throws IOException {
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
