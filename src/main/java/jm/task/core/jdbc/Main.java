package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Seamanov", (byte) 21);
        userService.saveUser("Artem", "Galkin", (byte) 13);
        userService.saveUser("Sanya", "Oldest", (byte) 17);
        userService.saveUser("Fil", "Salimov", (byte) 29);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
