package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {

            Socket socket = new Socket(url.getHost(), url.getDefaultPort());

            //получаем OutputStream
            OutputStream outputStream = socket.getOutputStream();
            String req = "GET "+url.getFile()+" HTTP/1.1\r\n Host: "+url.getHost()+"\r\n\r\n";
            outputStream.write(req.getBytes());
            outputStream.flush();

            //читаем ответ
            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

/*
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();
*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}