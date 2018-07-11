package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {

    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest MyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            String str[] = MyTest.fullyQualifiedNames();
            for (String s:
                 str) {
                System.out.println(s);
            }
            return true;
        }
        else
            return false;
    }

    public static boolean printValues(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest MyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Class<?> classes[] = MyTest.value();
            for (Class t:
                 classes) {
                System.out.println(t.getSimpleName());
            }
            return true;
        }
        else
            return false;
    }
}
