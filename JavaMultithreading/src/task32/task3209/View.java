package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    /* Он должен устанавливать внешний вид и поведение (look and feel) нашего приложения такими же, как это определено в системе.
     * Конструктор не должен кидать исключений, только логировать их с помощью ExceptionHandler.
     * Подсказа: для реализации задания используй класс UIManager.
     */
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Новый" :
                controller.createNewDocument(); break;
            case "Открыть" :
                controller.openDocument(); break;
            case "Сохранить" :
                controller.saveDocument(); break;
            case "Сохранить как..." :
                controller.saveDocumentAs(); break;
            case "Выход" :
                controller.exit();
            case "О программе" :
                showAbout();
        }
    }

    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void exit() {
        this.controller.exit();
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);

        this.getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane scrollPaneHTML = new JScrollPane(htmlTextPane);
        //для компонента tabbedPane должна добавляться вкладка с именем "HTML" и созданным компонентом JScrollPane на базе htmlTextPane.
        tabbedPane.addTab("HTML", scrollPaneHTML);
        //для компонента tabbedPane должен добавляться слушатель TabbedPaneChangeListener через метод addChangeListener
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        JScrollPane scrollPaneText = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", scrollPaneText);

        //для компонента tabbedPane должен добавляться слушатель TabbedPaneChangeListener через метод addChangeListener
        tabbedPane.setPreferredSize(tabbedPane.getPreferredSize());
        //должен добавлять по центру панели контента текущего фрейма нашу панель с вкладками, через getContentPane().add()
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {
        //Метод selectedTabChanged() должен проверить, какая вкладка сейчас оказалась выбранной.
        int indexTab = tabbedPane.getSelectedIndex();
        if (indexTab == 0) {
            controller.setPlainText(plainTextPane.getText());
        } else if (indexTab == 1) {
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo() {
        this.undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        if (tabbedPane.getSelectedIndex() == 0)
            return true;
        else return false;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Eggs are not supposed to be green.",
                "Inane custom dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
