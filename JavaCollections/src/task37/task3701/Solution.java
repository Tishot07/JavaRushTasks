package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {

    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator {

        Iterator<T> iterator = Solution.super.iterator();
        int index = 0;

        @Override
        public boolean hasNext() {
            if (size() == 0)
                return false;

            if (index >= size()) {
                iterator = Solution.super.iterator();
                index = 0;
            }

            return true;
        }

        @Override
        public Object next() {
            index++;
            return iterator.next();
        }

        @Override
        public void remove() {
            iterator.remove();
        }

        @Override
        public void forEachRemaining(Consumer action) {
            iterator.forEachRemaining(action);
        }

    }
}
