package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
@RequiredArgsConstructor
public class CommandLineDrive {

    private final UserService userService;

//    @Override
    public void run(String... args) {
        // Получение всех пользователей
        List<UserDto> allUsers = userService.getAllUsers();
        System.out.println("allUsers = " + allUsers);
        // Получение пользователя по ID
        UserDto user2 = userService.getUserById(2);
        System.out.println("user with id = 2 => " + user2);
        // Создание пользователя
        User ilonMask = userService.createUser("Ilon Mask");
        System.out.println("savedUser ilonMask = " + ilonMask);
        List<UserDto> afterAddUsers = userService.getAllUsers();
        System.out.println("afterAddUsers = " + afterAddUsers);
        // Удаление пользователя
        System.out.println("Deleting Ilon Mask...");
        userService.deleteUserById(ilonMask.getId());
        // Проверка того, что пользователь удалился
        List<UserDto> afterDeleteUsers = userService.getAllUsers();
        System.out.println("afterDeleteUsers = " + afterDeleteUsers);

        //    ===== Вывод =====
    /*
       Hibernate: select u1_0.id,u1_0.username from users u1_0
       allUsers = [UserDto[id=1, username=Ivan], UserDto[id=2, username=Maria], UserDto[id=3, username=Petr]]
       Hibernate: select u1_0.id,u1_0.username from users u1_0 where u1_0.id=?
       user with id = 2 => UserDto[id=2, username=Maria]
       Hibernate: insert into users (username) values (?)
       savedUser ilonMask = User{id=4, username='Ilon Mask'}
       Hibernate: select u1_0.id,u1_0.username from users u1_0
       afterAddUsers = [UserDto[id=1, username=Ivan], UserDto[id=2, username=Maria], UserDto[id=3, username=Petr], UserDto[id=4, username=Ilon Mask]]
       Deleting Ilon Mask...
       Hibernate: select u1_0.id,u1_0.username from users u1_0 where u1_0.id=?
       Hibernate: delete from users where id=?
       Hibernate: select u1_0.id,u1_0.username from users u1_0
       afterDeleteUsers = [UserDto[id=1, username=Ivan], UserDto[id=2, username=Maria], UserDto[id=3, username=Petr]]
    */
    }

}
