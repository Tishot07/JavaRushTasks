package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    //это Map<номинал, количество>
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        try {
            if (denominations.containsKey(denomination)) {
                denominations.put(denomination, denominations.get(denomination) + count);
            } else
                denominations.put(denomination, count);
        } catch (NullPointerException e) {
            e.getStackTrace();
        }

    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry:
             denominations.entrySet()) {
            result += entry.getKey() * entry.getValue();
        }
        return result;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        //return getTotalAmount() > expectedAmount ? true : false;
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        while(true) {
            int sum = expectedAmount;
            Map<Integer, Integer> result = new HashMap<>();
            //sorter nominal
            Map<Integer, Integer> map = new HashMap<>();
            map.putAll(denominations);
            List<Integer> list = new ArrayList<>(map.keySet());
            Collections.sort(list, Collections.reverseOrder());

            Iterator<Integer> iter = list.iterator();
            while (iter.hasNext()) {
                int temp = (Integer) iter.next();
                //количество банкнот
                int count = map.get(temp);
                while (temp <= sum && count > 0) {
                    sum -= temp;
                    --count;
                    if (result.containsKey(temp)) {
                        result.put(temp, result.get(temp) + 1);
                    } else {
                        result.put(temp, 1);
                    }
                }
            }
            //если номинал подобрали и все хорошо
            if (sum == 0) {
                //fix map
                for (Integer i:
                     result.keySet()) {
                    denominations.put(i, denominations.get(i) - result.get(i));
                    if (denominations.get(i) == 0) {
                        denominations.remove(i);
                    }
                }
                return result;

            } else {
                int nominal = list.get(0);
                list.remove(0);
                if ((getTotalAmount() - nominal < expectedAmount) || list.isEmpty()) {
                    throw new NotEnoughMoneyException();

                }
                System.out.println("Мы тут и тут все плохо");
                return null;
            }
        }

    }
}

