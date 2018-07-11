package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient bot = new BotClient();
        bot.run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String userName; String text;
            if(message.contains(":")) {
                userName = message.substring(0, message.indexOf(": "));
                text = message.substring(message.indexOf(": ") + 2);
            } else return;

            if (text.equals("дата"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime())));
            else if (text.equals("день"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("d").format(Calendar.getInstance().getTime())));
            else if (text.equals("месяц"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime())));
            else if (text.equals("год"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime())));
            else if (text.equals("время"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime())));
            else if (text.equals("час"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("H").format(Calendar.getInstance().getTime())));
            else if (text.equals("минуты"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("m").format(Calendar.getInstance().getTime())));
            else if (text.equals("секунды"))
                sendTextMessage(String.format("Информация для %s: %s",userName,new SimpleDateFormat("s").format(Calendar.getInstance().getTime())));
        }

    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {

        return "date_bot_" + (int)(Math.random()*100);
    }
}
