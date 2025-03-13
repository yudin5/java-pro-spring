package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Транзакции не будет при работе через CommandLineRunner, так как прокси не будет.
    // Но всё равно поставим, так как подобный код всегда должен вызывать другой сервис извне.
    @Transactional
    public User createUser(String username) {
        User user = new User(username);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    // Возвращаем ДТО через простейший маппинг
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Пользователь с ID = %s не найден", id)));
        return new UserDto(user.getId(), user.getUsername());
    }

    // Возвращаем список ДТО через простейший маппинг
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> new UserDto(user.getId(), user.getUsername()))
                .toList();
    }

}
