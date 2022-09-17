package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.service.*;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        //Util.getSessionFactory();

        userService.createUsersTable();

        userService.saveUser("Name1", "SurName1", (byte) 10);
        userService.saveUser("Name2", "SurName2", (byte) 20);
        userService.saveUser("Name3", "SurName3", (byte) 30);
        userService.saveUser("Name4", "SurName4", (byte) 40);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
