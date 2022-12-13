package ru.croc.task18.dao;

import ru.croc.task17.shop.objects.User;
import java.sql.*;

public class UserDao {
    private final String dbPath;
    private final String dbUsername;
    private final String dbPassword;
    public UserDao(String dbPath, String dbUsername, String dbPassword) {
        this.dbPath = dbPath;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public User findUser(String userName) {
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            try (PreparedStatement product = con.prepareStatement("select * from users where name=?")) {
                product.setString(1, userName);
                try (ResultSet resultSet = product.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(resultSet.getInt("id"), resultSet.getString("name"));
                    } else return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
