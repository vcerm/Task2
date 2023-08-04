package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alesha", "Popovich", (byte) 23);
        userService.saveUser("Baton", "Batonovich", (byte) 27);
        userService.saveUser("Kolya", "Kirkorovich", (byte) 35);
        userService.saveUser("Bob", "Bobovich", (byte) 23);
        List<User> list = userService.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
