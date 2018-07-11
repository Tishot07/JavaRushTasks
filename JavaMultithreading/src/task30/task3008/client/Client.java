package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.*;

import java.io.IOException;
import java.net.Socket;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected;

    public static void main(String[] args) {
        //Метод main должен создавать новый объект типа Client и вызывать у него метод run.
        new Client().run();
    }

    public void run() {
        //Метод run должен создавать
        SocketThread socketThread = new SocketThread();
        // запускать новый поток, полученный с помощью метода getSocketThread.
        socketThread = getSocketThread();
        //Поток созданный с помощью метода getSocketThread должен быть отмечен как демон
        socketThread.setDaemon(true);
        //После запуска нового socketThread
        socketThread.start();
        try {
            synchronized (this) {
                //метод run должен ожидать до тех пор, пока не будет пробужден
                this.wait();
            }
        } catch (Exception e) {return;}
        //До тех пор, пока флаг clientConnected равен true,с консоли должны считываться сообщения с помощью метода ConsoleHelper.readString.
        while (clientConnected) {
            String str = ConsoleHelper.readString();
            if (!str.equals("exit")) {
                if (shouldSendTextFromConsole() == true) {
                    sendTextMessage(str);
                }
            } else break;

        }

    }

    public class SocketThread extends Thread {

        //Метод processIncomingMessage должен выводить на экран сообщение полученное в качестве параметра
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        //Метод informAboutAddingNewUser должен выводить на экран сообщение о том что пользователь подключился к чату.
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("участник с именем " + userName + " присоединился к чату");
        }

        //Метод informAboutDeletingNewUser должен выводить на экран сообщение о том что пользователь покинул чат
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("участник с именем " + userName + " покинул чат");
        }

        //Метод notifyConnectionStatusChanged должен устанавливать значение поля clientConnected внешнего объекта Client равным полученному параметру
        //Метод notifyConnectionStatusChanged должен вызвать метод notify на внешнем объекте Client
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
               if (connection.receive().getType()==(MessageType.NAME_REQUEST))
                   connection.send(new Message(MessageType.USER_NAME, getUserName()));
               else if (connection.receive().getType()==(MessageType.NAME_ACCEPTED)) {
                   notifyConnectionStatusChanged(true);
                   return;
               } else throw new IOException("Unexpected MessageType");
            }

        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType()==(MessageType.TEXT))
                    processIncomingMessage(message.getData());
                else if (message.getType()==(MessageType.USER_ADDED))
                    informAboutAddingNewUser(message.getData());
                else if (message.getType()==(MessageType.USER_REMOVED))
                    informAboutDeletingNewUser(message.getData());
                else throw new IOException("Unexpected MessageType");
            }
        }

        public void run() {
            Socket socket;
            try {
                //(для получения адреса сервера и порта используй методы getServerAddress и getServerPort).
                String serverAddress = getServerAddress();
                int serverPort = getServerPort();
                socket = new Socket(serverAddress, serverPort);
                //В методе run должно быть установлено и сохранено в поле connection соединение с сервером
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {notifyConnectionStatusChanged(false);}
            catch (ClassNotFoundException e1) {notifyConnectionStatusChanged(false);}



        }

    }

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {return true;}

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            clientConnected = false;
        }
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

}
