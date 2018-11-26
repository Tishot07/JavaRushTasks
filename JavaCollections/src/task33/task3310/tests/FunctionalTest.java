package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {


    public void testStorage(Shortener shortener) {

        String str1 = Helper.generateRandomString();
        String str2 = Helper.generateRandomString();
        String str3 = new String(str1);

        Long idStr1 = shortener.getId(str1);
        Long idStr2 = shortener.getId(str2);
        Long idStr3 = shortener.getId(str3);

        Assert.assertNotEquals(idStr1, idStr2);
        Assert.assertNotEquals(idStr3, idStr2);
        Assert.assertEquals(idStr1, idStr3);

        String str1Id = shortener.getString(idStr1);
        String str2Id = shortener.getString(idStr2);
        String str3Id = shortener.getString(idStr3);

        Assert.assertEquals(str1Id, str1);
        Assert.assertEquals(str2Id, str2);
        Assert.assertEquals(str3Id, str3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}
