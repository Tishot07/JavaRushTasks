package com.javarush.task.task23.task2305;

import java.util.ArrayList;
import java.util.List;

/*
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        List<Solution> s = new ArrayList<>();

        s.add(new Solution());
        s.add(new Solution());
        for (Solution sol:
             s) {
            sol.innerClasses[0] = sol.new InnerClass();
            sol.innerClasses[1] = sol.new InnerClass();
        }
        return s.toArray(new Solution[s.size()]);
    }

    public static void main(String[] args) {

    }
}
