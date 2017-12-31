package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tishort on 15.07.17.
 */
public class Hippodrome {

    public static Hippodrome game;

    private List<Horse> horses;

    public Hippodrome(List<Horse> horse) {
        horses = horse;
    }

    public List<Horse> getHorses() {return horses;}

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse h:
             horses) {
            h.move();
        }
    }

    public void print() {
        for (Horse h:
             horses) {
            h.print();
        }
        for (int i = 0; i < 10; i++)
            System.out.println();
    }

    public Horse getWinner() {
        double max = 0;
        for (Horse h:
             horses) {
            if (h.getDistance() > max) {
                max = h.getDistance();
            }
        }
        int index = -1;
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getDistance() == max)
                index = i;
        }
        return horses.get(index);
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException {


        List<Horse> list = new ArrayList<>();
        list.add(new Horse("Name1", 3,0));
        list.add(new Horse("Name2", 3,0));
        list.add(new Horse("Name3", 3,0));
        game = new Hippodrome(list);
        game.run();
        game.printWinner();

    }
}
