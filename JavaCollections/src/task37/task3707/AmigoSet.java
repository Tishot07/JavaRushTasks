package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int)(collection.size()/0.75f +1));
        map = new HashMap<E, Object>(capacity);
        addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        Set<E> set = map.keySet();
        return set.iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {

        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {

        return map.remove(o)==PRESENT;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet<E> copy = (AmigoSet<E>)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();

    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

    }
}
