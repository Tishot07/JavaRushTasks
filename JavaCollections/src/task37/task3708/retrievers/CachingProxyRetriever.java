package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private LRUCache cache;
    private OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
        cache = new LRUCache(10);
    }

    @Override
    public Object retrieve(long id) {
        Object result = null;
        result = cache.find(id);
        if (result != null)
            return result;
        else {
            result = originalRetriever.retrieve(id);
            cache.set(id, result);
            return result;
        }

    }
}
