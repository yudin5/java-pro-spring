package org.example.service;

import org.example.dao.UserDao;
import org.example.dto.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int createUser(long id, String username) throws SQLException {
        return userDao.createUser(id, username);
    }

    public boolean deleteUserById(long id) throws SQLException {
        return userDao.deleteUserById(id);
    }

    public User getUserById(long id) throws SQLException {
        return userDao.findById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAll();
    }
}
