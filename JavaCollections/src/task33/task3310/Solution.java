package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        HashMapStorageStrategy hashMapStratedy = new HashMapStorageStrategy();
        testStrategy(hashMapStratedy, 10000);

        OurHashMapStorageStrategy ourStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourStrategy, 10000);

        FileStorageStrategy fileStrategy = new FileStorageStrategy();
        testStrategy(fileStrategy, 100);

        OurHashBiMapStorageStrategy ourHashBiMap = new OurHashBiMapStorageStrategy();
        testStrategy(ourHashBiMap, 10000);

        HashBiMapStorageStrategy hashBiMap = new HashBiMapStorageStrategy();
        testStrategy(hashBiMap, 10000);

        DualHashBidiMapStorageStrategy dualHashBidiMap = new DualHashBidiMapStorageStrategy();
        testStrategy(dualHashBidiMap, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> resultSet = new HashSet<>();
        for (String str:
             strings) {
            resultSet.add(shortener.getId(str));
        }

        return resultSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> resultSet = new HashSet<>();
        for (Long id:
                keys) {
            resultSet.add(shortener.getString(id));
        }

        return resultSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testSetString = new HashSet<>();
        Set<Long> testSetLong = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetString.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date dateStart = new Date();
        testSetLong = getIds(shortener, testSetString);
        Date dateStop = new Date();
        long msec = dateStop.getTime() - dateStart.getTime();
        Helper.printMessage("time long: " + msec);

        Set<String> testSetStringMethod = new HashSet<>();
        Date start = new Date();
        testSetStringMethod = getStrings(shortener, testSetLong);
        Date stop = new Date();
        long msec2 = stop.getTime() - start.getTime();
        Helper.printMessage("time string: " + msec2);

        if (testSetString.containsAll(testSetStringMethod))
            Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
