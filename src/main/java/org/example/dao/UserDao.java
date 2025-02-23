package org.example.dao;

import org.example.dto.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private final DataSource dataSource;

    private UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int createUser(long id, String username) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (id, username) VALUES (?, ?)");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, username);
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Создано записей: " + rowsInserted);
            return rowsInserted;
        }
    }

    public boolean deleteUserById(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Удалено записей: " + rowsDeleted);
            return rowsDeleted > 0;
        }
    }

    public User findById(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }
            User user = new User();
            user.setId(result.getLong(1));
            user.setUsername(result.getString(2));
            return user;
        }
    }

    public List<User> getAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM users");

            List<User> list = new ArrayList<>();
            while (results.next()) {
                User user = new User();
                user.setId(results.getLong(1));
                user.setUsername(results.getString(2));
                list.add(user);
            }
            return list;
        }
    }

}
