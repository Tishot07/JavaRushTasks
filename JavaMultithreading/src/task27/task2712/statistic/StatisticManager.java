package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    //private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return instance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    /*
    public void register(Cook cook) {
        cooks.add(cook);
    }
    */
    
    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (int i = 0; i < EventType.values().length; i++) {
                storage.put(EventType.values()[i], new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            EventType type = data.getType();
            List<EventDataRow> list = storage.get(type);
            list.add(data);
            storage.put(type, list);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public Map<Date, Double> acountMoney() {
        Map<Date, Double> result = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        double money = 0;
        Date day;
        Calendar calendar;
        for (EventDataRow temp :
                list) {
            VideoSelectedEventDataRow video = (VideoSelectedEventDataRow) temp;
            money = video.getAmount()/100.0;
            calendar = Calendar.getInstance();
            calendar.setTime(video.getDate());
            GregorianCalendar gc = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            day = gc.getTime();
            if (result.containsKey(day))
                money += result.get(day);
            result.put(day, money);
        }
        return result;
        //*/
    }

    public Map<Date, Map<String, Integer>> timeCook() {
        Map<Date, Map<String, Integer>> result = new HashMap<>();
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        for (EventDataRow temp : list) {
            CookedOrderEventDataRow cookedEvent = (CookedOrderEventDataRow) temp;
            Date date = cookedEvent.getDate();
            Map<String, Integer> cookMap = new TreeMap<>();

            for (EventDataRow temp2 : list) {
                CookedOrderEventDataRow cookedOrderEv = (CookedOrderEventDataRow) temp2;
                String nameOfCook = cookedOrderEv.getCookName();
                int cookTime = cookedOrderEv.getTime();

                if (cookedEvent.getDate() == date) {
                    if (!cookMap.containsKey(nameOfCook)) {
                        cookMap.put(nameOfCook, cookTime);
                    } else {
                        int tempTime = cookMap.get(nameOfCook) + temp2.getTime();
                        cookMap.put(nameOfCook, tempTime);
                    }
                }
            }

            result.put(date, cookMap);
        }

        return result;
    }

    /*
    public Set<Cook> getCooks() {
        return cooks;
    }
    */
}
