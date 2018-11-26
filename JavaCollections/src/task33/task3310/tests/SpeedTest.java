package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {

        Date start = new Date();
        for (String s:
             strings) {
            ids.add(shortener.getId(s));
        }
        Date stop = new Date();

        return stop.getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (Long l:
             ids) {
            strings.add(shortener.getString(l));
        }
        Date stop = new Date();
        return stop.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> id1 = new HashSet<>();
        Set<Long> id2 = new HashSet<>();

        long time1 = getTimeForGettingIds(shortener1, origStrings, id1);
        long time2 = getTimeForGettingIds(shortener2, origStrings, id2);
        assertTrue((time1 > time2));

        long time1Str = getTimeForGettingStrings(shortener1, id1, origStrings);
        long time2Str = getTimeForGettingStrings(shortener2, id2, origStrings);
        assertEquals(time1Str, time2Str, 30);

    }
}
