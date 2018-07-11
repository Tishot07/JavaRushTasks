package com.javarush.task.task29.task2907;

import java.math.BigDecimal;

/* 
Этот странный BigDecimal
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
        String v1s = new Double(v1).toString();

         return new BigDecimal(v1s).add(new BigDecimal(0).valueOf(v2));
    }
}