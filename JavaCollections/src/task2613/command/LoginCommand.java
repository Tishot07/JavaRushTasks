package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    //private String numberCard = "123456789012";
    //private String pin = "1234";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String s1 = ConsoleHelper.readString();
            String s2 = ConsoleHelper.readString();
            if (validCreditCards.containsKey(s1)) {
                if (validCreditCards.getString(s1).equals(s2))
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), s1));
                else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            break;
        }
        /*
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        String number = ConsoleHelper.readString();
        String pin = ConsoleHelper.readString();
        while (true) {
            if (validCreditCards.containsKey(number)) {
                if (validCreditCards.getString(number).equals(pin)){
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));
                    return;
                } else {
                    ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    number = ConsoleHelper.readString();
                    pin = ConsoleHelper.readString();
                }
            }
            //return;
        }
        */
    }
}
