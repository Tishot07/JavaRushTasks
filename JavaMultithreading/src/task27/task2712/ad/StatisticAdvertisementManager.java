package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    private StatisticAdvertisementManager() {
    }

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public Map<String, Integer> active() {
        Map<String, Integer> map = new TreeMap<>();
        List<Advertisement> list = storage.list();
        for (Advertisement temp:
             list) {
            if (temp.getHits() > 0) {
                map.put(temp.getName(), temp.getHits());
            }
        }
        return map;
    }

    public List<String> passive() {
        List<String> result = new ArrayList<>();
        List<Advertisement> list = storage.list();
        for (Advertisement temp:
             list) {
            if (temp.getHits() == 0) {
                result.add(temp.getName());
            }
        }
        return result;
    }
}
