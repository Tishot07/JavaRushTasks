package com.javarush.task.task37.task3702.male;


import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        if (age <= 12)
            return (Human) new KidBoy();
        else if (age <= 19)
            return (Human) new TeenBoy();
        else return (Human) new Man();
    }
}
