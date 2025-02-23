package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public int createUser(long id, String username) throws SQLException {
//        return userDao.createUser(id, username);
//    }
//
//    public boolean deleteUserById(long id) throws SQLException {
//        return userDao.deleteUserById(id);
//    }

    public User getUserById(long id) throws SQLException {
        return userRepository.findById(id).orElse(null);
    }

//
//    public List<User> getAllUsers() throws SQLException {
//        return userDao.getAll();
//    }
}
