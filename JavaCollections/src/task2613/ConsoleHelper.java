package com.javarush.task.task26.task2613;


import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    static private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".common_en");

    static final private BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String temp = bis.readLine();
            if (temp.equalsIgnoreCase("EXIT"))
                throw new InterruptOperationException();
            else
                return temp;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        //writeMessage(res.getString("choose.currency.code"));
        String code = readString();
        while(true) {
            if (code.length() == 3) {
                return code.toUpperCase();
            }
            else {
                //writeMessage(res.getString("invalid.data"));
                code = readString();
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        //ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        //String[] line = readString().split(" ");
        String[] result = new String[2];
        String str = readString();
        while(true) {
            if (str.matches("\\d+\\s\\d+")) {
                String[] line = str.split(" ");
                return line;
            } else {
                //writeMessage(res.getString("invalid.data"));
                //writeMessage(res.getString("invalid.data"));
                str = readString();
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        //writeMessage(res.getString("choose.operation"));
        String str = readString();
        while (true) {
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(str));
            } catch (Exception e) {
                //writeMessage(res.getString("invalid.data"));
                throw new InterruptOperationException();
                //str = readString();
                //continue;
            }
        }
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}