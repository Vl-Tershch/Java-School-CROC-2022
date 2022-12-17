package ru.croc.finaltask.school.dao;

import ru.croc.finaltask.school.objects.User;
import java.sql.*;

public final class UserDao {
    private final Connection connection;
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User findUser(String userName) {
        try (PreparedStatement user = this.connection.prepareStatement("select * from users where users.username=?")) {
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
        User existsUser = findUser(userName);
        if (existsUser == null) {
            Integer newId = findMaxId();
            String sqlUser = "insert into users(id, username, password) values (" + (newId + 1) + ",'" + userName +
                    "','" + password + "')";
            try (Statement statement = this.connection.createStatement()) {
                statement.execute(sqlUser);
                System.out.println("Вы зарегистрировались!");
                return findUser(userName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Данный пользователь уже существует в системе!");
        }
        return null;
    }

    public int findMaxId() {
        try (PreparedStatement order = connection.prepareStatement("select MAX(id) AS max_id from users")) {
            try (ResultSet resultSet = order.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("max_id");
                } else return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
