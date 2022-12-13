package ru.croc.task18.dao;

import ru.croc.task17.shop.objects.Order;
import ru.croc.task17.shop.objects.Product;
import ru.croc.task17.shop.objects.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private final String dbPath;
    private final String dbUsername;
    private final String dbPassword;
    public OrderDao(String dbPath, String dbUsername, String dbPassword) {
        this.dbPath = dbPath;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public Order findOrderById(Integer orderId) {
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            try (PreparedStatement order = con.prepareStatement("select * from orders where id=?")) {
                order.setInt(1, orderId);
                try (ResultSet resultSet = order.executeQuery()) {
                    if (resultSet.next()) {
                        return new Order(resultSet.getInt("id"), resultSet.getInt("user_id"),
                                resultSet.getInt("product_id"));
                    } else return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findMaxId() {
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            try (PreparedStatement order = con.prepareStatement("select MAX(id) AS max_id from orders")) {
                try (ResultSet resultSet = order.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("max_id");
                    } else return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> createOrder(String userLogin, List<Product> products) {
        UserDao userDao = new UserDao(this.dbPath, dbUsername, dbPassword);
        ProductDao productDao = new ProductDao(this.dbPath, dbUsername, dbPassword);
        User userHelp = userDao.findUser(userLogin);
        List<Order> result = new ArrayList<>();
        int ordersCount = 0;

        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            if (userHelp == null) {
                return null;
            } else {
                try (Statement statement = con.createStatement()) {
                    for (Product product : products) {
                        if (productDao.findProduct(product.getArcticle()) == null) {
                            productDao.createProduct(product);
                        }
                        ordersCount = findMaxId();
                        statement.execute("insert into orders(id, user_id, product_id) values (" + (ordersCount + 1) +
                                "," + userHelp.getId() + "," + product.getId() + ")");
                        result.add(findOrderById(ordersCount));
                    }
                }
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
