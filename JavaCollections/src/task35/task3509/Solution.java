package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);
        }

        return (ArrayList<T>) list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        Set<T> set = new HashSet<>();
        for (int i = 0; i < elements.length; i++) {
            set.add(elements[i]);
        }
        return (HashSet<T>) set;
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        Map<K, V> map = new HashMap<>();
        if (keys.size() != values.size())
            throw new IllegalArgumentException();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return (HashMap<K, V>) map;
    }
}
