package ru.croc.task18.dao;

import ru.croc.task17.shop.objects.Order;
import ru.croc.task17.shop.objects.User;
import ru.croc.task19.objects.UserResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<UserResponse> findDeliveryDateById(Integer userId) {
        List<UserResponse> userResponses = new ArrayList<>();
        List<Order> userOrders = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            try (PreparedStatement orders = con.prepareStatement("select * from orders where user_id=?")) {
                orders.setInt(1, userId);
                try (ResultSet resultSet = orders.executeQuery()) {
                    while (resultSet.next()) {
                         userOrders.add(new Order(resultSet.getInt("id"),
                                 resultSet.getInt("id"), resultSet.getInt("user_id")));
                    }
                }
            }
            for (Order order : userOrders) {
                String curDate = "";
                int curId = 0;
                String curName = "";
                try (PreparedStatement deliveries = con.prepareStatement("select * from deliveries where order_id=?")) {
                    deliveries.setInt(1, order.getId());
                    try (ResultSet resultSet = deliveries.executeQuery()) {
                        if (resultSet.next()) {
                            curDate = resultSet.getString("delivery_time");
                            curId = resultSet.getInt("courier_id");
                        }
                    }
                }
                try (PreparedStatement courier = con.prepareStatement("select * from couriers where id=?")) {
                    courier.setInt(1, curId);
                    try (ResultSet resultSet = courier.executeQuery()) {
                        if (resultSet.next()) {
                            curName = resultSet.getString("first_name") + " : "
                                    + resultSet.getString("last_name");
                        }
                    }
                }
                userResponses.add(new UserResponse(curDate, curName));
            }
            return userResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
