package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleHelper {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        Dish.allDishesToString();
        List<Dish> result = new ArrayList<>();
        boolean add = false;
            while (true){
                String dish = readString();
                if(dish.equalsIgnoreCase("exit"))
                    break;
                try{
                    result.add(Dish.valueOf(dish));
                }catch (Exception e){
                    writeMessage("Такого блюда нет в меню.");
                }
            }
        return result;
    }
}
