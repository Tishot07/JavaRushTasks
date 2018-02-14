package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {
    private View view;

    public TextEditMenuListener(View view) { this.view = view;}

    //Метод menuSelected(MenuEvent menuEvent) класса TextEditMenuListener должен устанавливать доступность пунктов меню стиль,
    // выравнивание, цвет и шрифт в зависимости от выбранной вкладки
    @Override
    public void menuSelected(MenuEvent e) {
        //Из переданного параметра получать объект, над которым было совершено действие. В нашем случае это будет объект с типом JMenu.
        JMenu jMenu = (JMenu) e.getSource();
        //У полученного меню получать список компонентов (пунктов меню)
        Component[] list = jMenu.getMenuComponents();
        for (int i = 0; i < list.length; i++) {
            list[i].setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
