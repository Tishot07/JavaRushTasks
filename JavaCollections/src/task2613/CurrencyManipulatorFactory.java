package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        for (String s:
             map.keySet()) {
            if (s.equalsIgnoreCase(currencyCode)) {
                return map.get(s);
            }
        }
            CurrencyManipulator temp = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, temp);
            return temp;

    }

    private CurrencyManipulatorFactory() {}

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
