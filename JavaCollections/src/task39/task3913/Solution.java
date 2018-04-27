package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("/home/tishort"));

        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println("-----------");
        System.out.println(logParser.getIPsForUser("Amigo", null, new Date()));
        System.out.println(logParser.getIPsForUser("Vasya Pupkin", null, new Date()));
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, new Date()));
        System.out.println("-----------");
        System.out.println(logParser.getIPsForEvent(Event.LOGIN, null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.DOWNLOAD_PLUGIN, null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.WRITE_MESSAGE, null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.SOLVE_TASK, null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, new Date()));
        System.out.println("-----------");
        System.out.println(logParser.getIPsForStatus(Status.OK, null, new Date()));
        System.out.println(logParser.getIPsForStatus(Status.FAILED, null, new Date()));
        System.out.println(logParser.getIPsForStatus(Status.ERROR, null, new Date()));

    }
}