package com.javarush.task.task40.task4008;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        Date d = null;
        if (date.contains(".") && date.contains(":")) {
            String[] temp = date.split(" ");
            date(temp[0]);
            time(temp[1]);

        } else if (date.contains(".")) {
            date(date);

        } else if (date.contains(":")) {
            time(date);
        }
    }

    private static void date(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        System.out.println("День: " + dateTime.getDayOfMonth());
        System.out.println("День недели: " + dateTime.getDayOfWeek().getValue());
        System.out.println("День месяца: " + dateTime.getDayOfMonth());
        System.out.println("День года: " + dateTime.getDayOfYear());
        System.out.println("Неделя месяца: " + dateTime.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
        System.out.println("Неделя года: " + dateTime.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
        System.out.println("Месяц: " + dateTime.getMonthValue());
        System.out.println("Год: " + dateTime.getYear());
    }

    private static void time(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m:s");
        LocalTime dateTime = LocalTime.parse(date, formatter);
        System.out.println("AM или PM: " + (dateTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
        System.out.println("Часы: " + dateTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("Часы дня: " + dateTime.getHour());
        System.out.println("Минуты: " + dateTime.getMinute());
        System.out.println("Секунды: " + dateTime.getSecond());
    }
}