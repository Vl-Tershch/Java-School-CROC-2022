package ru.croc.finaltask.school.dao;

import ru.croc.finaltask.school.objects.User;
import java.sql.*;

public final class UserDao {
    private final Connection connection;
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User findUser(String userName) {
        try (PreparedStatement user = this.connection.prepareStatement("select * from user where " +
                "user.username=?")) {
            user.setString(1, userName);
            try (ResultSet resultSet = user.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("username"),
                            resultSet.getString("password"));
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User createUser(String userName, String password) {
        if (findUser(userName) == null) {
            String sqlUser = "insert into user(username, password) values ('" + userName +
                    "','" + password + "')";
            try (Statement statement = this.connection.createStatement()) {
                statement.execute(sqlUser);
                System.out.println("[SYS]: Вы зарегистрировались!");
                return findUser(userName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("[SYS]: Данный пользователь уже существует в системе!");
        }
        return null;
    }
}
