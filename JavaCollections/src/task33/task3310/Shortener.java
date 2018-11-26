package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    private StorageStrategy storageStrategy;
    private Long lastId;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
        this.lastId = 0L;
    }

    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string))
            return storageStrategy.getKey(string);
        else {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }


}
