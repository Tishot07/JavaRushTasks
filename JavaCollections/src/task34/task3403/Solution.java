package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {

    public void recursion(int n) {
        if (n > 1) { for (int i = 2; i <= n; i++) { if (n % i == 0) { System.out.println(i); n = n / i; recursion(n); return; } } }

    }
}
