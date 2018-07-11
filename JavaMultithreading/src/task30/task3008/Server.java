package com.javarush.task.task30.task3008;

import com.javarush.task.task30.task3008.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            System.out.println("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ConsoleHelper.writeMessage("установлено новое соединение c " + socket.getRemoteSocketAddress());
                Connection con = new Connection(socket);
                String name = serverHandshake(con);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                sendListOfUsers(con, name);
                serverMainLoop(con, name);

                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
            } catch (IOException e) {}
            catch (ClassNotFoundException el) {}
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name = null;
            while (true) {
                //Сформировать и отправить команду запроса имени пользователя
                connection.send(new Message(MessageType.NAME_REQUEST));
                //Получить ответ клиента
                Message message = connection.receive();
                //Проверить, что получена команда с именем пользователя
                if (message.getType() == MessageType.USER_NAME){
                    //Достать из ответа имя, проверить, что оно не пустое и пользователь с таким именем еще не подключен
                    name = message.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        //Добавить нового пользователя и соединение с ним в connectionMap
                        connectionMap.put(name, connection);
                        //Отправить клиенту команду информирующую, что его имя принято
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            //Пройтись по connectionMap
            for (String str:
                 connectionMap.keySet()) {
                //Команду с типом USER_ADDED и именем равным userName отправлять не нужно
                if (str.equals(userName)) continue;
                // У каждого элемента получить имя клиента, сформировать команду с типом USER_ADDED и полученным именем, Отправить сформированную команду через connection
                connection.send(new Message(MessageType.USER_ADDED, str));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while(true){
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT){
                    String s = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT,s));
                }else{
                    ConsoleHelper.writeMessage("Error");
                }
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Connection connection:
                connectionMap.values()) {
           try {
               connection.send(message);
           } catch (IOException e) {
               e.printStackTrace();
               ConsoleHelper.writeMessage("Сообщение не отправлено");
           }
        }
    }

}
