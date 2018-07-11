package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {}

    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        private StringBuilder builder = new StringBuilder();
        private BufferedReader reader;

        public void setFileName(String fullFileName) {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(fullFileName)));;
            } catch (Exception e) {}

        }

        public String getFileContent() {
            try {
                return builder.toString().trim();
            } catch (NullPointerException e) {
                System.out.println(e);
                return null;
            }
        }

        public void run() {
            String str;

            try {

                try {
                    while ((str = reader.readLine()) != null) {
                        if (str.trim().length() != 0) {
                            builder.append(str.trim() + " ");
                        }
                    }
                    reader.close();
                    builder.toString();
                } catch (IOException e) {
                }
            } catch (Exception e) {
            }
        }
    }
}
