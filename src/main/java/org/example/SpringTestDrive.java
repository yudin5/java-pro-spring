package org.example;

import org.example.dto.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;
import java.util.List;

@ComponentScan
public class SpringTestDrive {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringTestDrive.class);
        UserService userService = context.getBean(UserService.class);

        // Создание пользователей
        userService.createUser(1, "Ivan");
        userService.createUser(2, "Maria");
        userService.createUser(3, "Petr");

        // Удаление пользователя
        boolean deleted = userService.deleteUserById(3);
        System.out.println("User deleted = " + deleted);

        // Получение пользователя
        User user2 = userService.getUserById(2);
        System.out.println("user2 = " + user2);

        User user100500 = userService.getUserById(100500); // Несуществующий user
        System.out.println("user100500 = " + user100500);

        // Получение всех пользователей
        List<User> allUsers = userService.getAllUsers();
        System.out.println("allUsers = " + allUsers);
    }

}