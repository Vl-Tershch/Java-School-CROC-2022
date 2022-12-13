package ru.croc.task17.shop;

import ru.croc.task17.shop.objects.DatabaseData;
import ru.croc.task17.shop.objects.Order;
import ru.croc.task17.shop.objects.Product;
import ru.croc.task17.shop.objects.User;
import ru.croc.task19.objects.Courier;
import ru.croc.task19.objects.Delivery;
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

            // Tables for Task 19
            statement.execute("create table couriers(id integer primary key not null, number integer not null," +
                    " first_name varchar, last_name varchar)");
            statement.execute("create table deliveries(id integer primary key not null, order_id integer not null," +
                    " courier_id integer not null, delivery_time varchar not null, foreign key (order_id) references orders(id), " +
                    "foreign key (courier_id) references couriers(id))");
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
                        order.getUserId() + "," + order.getProductId() + ")";
                statement.execute(sqlOrders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[TRANSACTION]: Orders update success!");
    }

    public void updateCouriers(List<Courier> list) {
        try (Statement statement = getConnection().createStatement()) {
            for (Courier courier : list) {
                String sqlCouriers = "insert into couriers(id, number, first_name, last_name) values (" + courier.getId()
                        + "," + courier.getNumber() + ",'" + courier.getFirstName() + "','"
                        + courier.getLastName() + "')";
                statement.execute(sqlCouriers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[TRANSACTION]: Couriers update success!");
    }

    public void updateDeliveries(List<Delivery> list) {
        try (Statement statement = getConnection().createStatement()) {
            for (Delivery delivery : list) {
                String sqlDeliveries = "insert into deliveries(id, order_id, courier_id, delivery_time) values ("
                        + delivery.getId() + "," + delivery.getOrderId() + "," + delivery.getCourierId() + ",'"
                        + delivery.getDeliveryTime() + "')";
                statement.execute(sqlDeliveries);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[TRANSACTION]: Deliveries update success!");
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

            System.out.println("\n________ COURIERS ________");
            ResultSet couriersData = statement.executeQuery("select * from couriers");
            while (couriersData.next()) {
                System.out.println("Courier {id=" + couriersData.getInt("id") + "}: "
                        + "number: " + couriersData.getInt("number") + ", "
                        + "first_name: " + couriersData.getString("first_name") + ", "
                        + "last_name: " + couriersData.getString("last_name"));
            }

            System.out.println("\n________ DELIVERIES ________");
            ResultSet deliveriesData = statement.executeQuery("select * from deliveries");
            while (deliveriesData.next()) {
                System.out.println("Delivery {id=" + deliveriesData.getInt("id") + "}: order id: {id="
                        + deliveriesData.getInt("order_id") + "}, courier id: {id="
                        + deliveriesData.getInt("courier_id") + "}, date : {delivery_time='"
                        + deliveriesData.getString("delivery_time") + "'}");
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
