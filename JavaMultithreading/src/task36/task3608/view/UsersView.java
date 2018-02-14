package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.*;
import com.javarush.task.task36.task3608.model.*;

import java.util.List;


public class UsersView implements View {
    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void refresh(ModelData modelData) {
        if (modelData.isDisplayDeletedUserList()==true)
            System.out.println("All deleted users:");
        else
            System.out.println("All users:");
        List<User> user = modelData.getUsers();
        for (User u: user) {
            System.out.println("\tUser{name='" +u.getName() + "', id=" +u.getId() + ", level=" +u.getLevel() + "}");
        }
        System.out.println("===================================================");
    }

    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }



}
