package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        String laters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "1234567890";
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < 4; i++) {
            result.append(laters.charAt((int)(Math.random() * 26)));
        }
        for (int i = 0; i < 2; i++) {
            result.append(digits.charAt((int)(Math.random() * 10)));
        }
        for (int i = 0; i < 2; i++) {
            result.append(laters.toUpperCase().charAt((int)(Math.random() * 26)));
        }

        try {
            output.write(result.toString().getBytes());
        } catch (Exception e) {}

        return output;
    }

    /* Работает, но валидатор не принимает
    public static ByteArrayOutputStream getPassword() throws IOException {
        //number
        int num = (int) (Math.random() * (58-48)) + 48;

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte buf[] = new byte[8];
        buf[0] = (byte) getAZUpp();
        buf[1] = (byte) num;
        for (int i = 2; i < 8; i++) {
            buf[i] = (byte) getAZ();
        }
        result.write(buf);

        return result;
    }

    public static char getAZUpp() {
        return (char) ((char) (Math.random() * (91-64)) + 64);
    }

    public static char getAZ() {
        return (char) ((char) (Math.random() * (123-97)) + 97);
    }
    */
}