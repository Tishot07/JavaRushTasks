package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        Map<Date, Double> map = StatisticManager.getInstance().acountMoney();
        SimpleDateFormat data = new SimpleDateFormat("dd-MMM-yyyy");
        double total = 0;
        for (Map.Entry<Date, Double> temp:
             map.entrySet()) {
            double money = temp.getValue();
            if (money > 0) {
                //ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%1$te-%1$tb-%1$tY - %2$.2f", temp.getKey(), money));
                String d = data.format(temp.getKey());
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", d, money));
                total += money;
            }
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", total));
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> map = StatisticManager.getInstance().timeCook();
        for (Map.Entry entry : map.entrySet()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String date = simpleDateFormat.format(entry.getKey());
            ConsoleHelper.writeMessage(date);
            Map<String, Integer> map1 = (Map) entry.getValue();

            for (Map.Entry entry1 : map1.entrySet()) {
                ConsoleHelper.writeMessage(entry1.getKey() + " - " + entry1.getValue() + " min");
            }
        }
    }

    public void printActiveVideoSet() {
        Map<String, Integer> map = StatisticAdvertisementManager.getInstance().active();
        for(Map.Entry entry : map.entrySet()){
            ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void printArchivedVideoSet() {
        List<String> list = StatisticAdvertisementManager.getInstance().passive();

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        for(String name : list){
            ConsoleHelper.writeMessage(name);
        }
    }
}
