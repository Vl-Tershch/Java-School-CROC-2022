package ru.croc.task17.shop;

import ru.croc.task17.shop.objects.DatabaseData;
import ru.croc.task17.shop.objects.Order;
import ru.croc.task17.shop.objects.Product;
import ru.croc.task17.shop.objects.User;
import java.sql.*;
import java.util.List;

public class ShopDatabaseLogic {
    private Connection connection;

    public ShopDatabaseLogic(Connection connection) {
        this.connection = connection;
    }

    public void createTables() {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute("create table users(id integer primary key not null, name varchar)");
            statement.execute("create table products(id integer primary key not null, article varchar not null, " +
                    "name varchar not null, price integer not null)");
            statement.execute("create table orders(id integer primary key not null, user_id integer not null," +
                    " product_id integer not null, foreign key (user_id) references users(id), " +
                    "foreign key (product_id) references products(id))");
            System.out.println("[TRANSACTION]: Successful created tables in database!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTables(String csvPath) {
        DatabaseData databaseData = CSVReader.createLists(csvPath);
        updateUsers(databaseData.getUsers());
        updateProducts(databaseData.getProducts());
        updateOrders(databaseData.getOrders());
    }

    public void updateUsers(List<User> list) {
        try (Statement statement = getConnection().createStatement()) {
            for (User user : list) {
                String sqlUsers = "insert into users(id, name) values (" + user.getId() + ",'" + user.getName() + "')";
                statement.execute(sqlUsers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[TRANSACTION]: Users update success!");
    }

    public void updateProducts(List<Product> list) {
        try (Statement statement = getConnection().createStatement()) {
            for (Product product : list) {
                String sqlProducts = "insert into products(id, article, name, price) values (" + product.getId() + ",'"
                        + product.getArcticle() + "','" + product.getName() + "'," + product.getPrice() + ")";
                statement.execute(sqlProducts);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[TRANSACTION]: Products update success!");
    }

    public void updateOrders(List<Order> list) {
        try (Statement statement = getConnection().createStatement()) {
            for (Order order : list) {
                String sqlOrders = "insert into orders(id, user_id, product_id) values (" + order.getId() + "," +
                        order.getUserid() + "," + order.getProductId() + ")";
                statement.execute(sqlOrders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[TRANSACTION]: Orders update success!");
    }

    public void printDatabase() {
        try (Statement statement = getConnection().createStatement()) {

            System.out.println("\n________ USERS ________");
            ResultSet usersData = statement.executeQuery("select * from users");
            while (usersData.next()) {
                System.out.println("User " + "{id=" + usersData.getInt("id") + "}: name: "
                        + usersData.getString("name"));
            }

            System.out.println("\n________ PRODUCTS ________");
            ResultSet productsData = statement.executeQuery("select * from products");
            while (productsData.next()) {
                System.out.println("Product {id=" + productsData.getInt("id") + "}: "
                        + "article: " + productsData.getString("article") + ", "
                        + "name: " + productsData.getString("name") + ", "
                        + "price: " + productsData.getInt("price"));
            }

            System.out.println("\n________ ORDERS ________");
            ResultSet ordersData = statement.executeQuery("select * from orders");
            while (ordersData.next()) {
                System.out.println("Order {id=" + ordersData.getInt("id") + "}: user id: {id="
                        + ordersData.getInt("user_id") + "}, product id: {id="
                        + ordersData.getInt("product_id") + "}");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}