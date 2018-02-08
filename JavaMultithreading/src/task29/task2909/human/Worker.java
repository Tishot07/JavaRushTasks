package com.javarush.task.task29.task2909.human;

public class Worker extends Human {
    //private Human human;
    private Soldier soldier;
    private double salary;
    private String company;

    public Worker(String name, int age) {
        super(name, age);
        //soldier = new Soldier(name, age);
    }

    public void live() {
        //human.live();
        soldier.live();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}