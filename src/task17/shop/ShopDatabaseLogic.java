package task17.shop;

import task17.shop.objects.Order;
import task17.shop.objects.Product;
import task17.shop.objects.User;
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
            statement.execute("create table products(id integer primary key not null, article varchar, " +
                    "name varchar, price integer)");
            statement.execute("create table orders(id integer primary key not null, user_id integer not null," +
                    " product_id integer not null, foreign key (user_id) references users(id), " +
                    "foreign key (product_id) references products(id))");
            System.out.println("Succesful created tables in database!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTables(String csvPath) {
        List<List> list = CSVReader.createLists(csvPath);
        updateUsers(list.get(0));
        updateProducts(list.get(1));
        updateOrders(list.get(2));
    }

    public void updateUsers(List<User> set) {
        try (Statement statement = getConnection().createStatement()) {
            for (User user : set) {
                String sqlUsers = "insert into users(id, name) values (" + user.getId() + ",'" + user.getName() + "')";
                statement.execute(sqlUsers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Users update success!");
    }

    public void updateProducts(List<Product> set) {
        try (Statement statement = getConnection().createStatement()) {
            for (Product product : set) {
                String sqlProducts = "insert into products(id, article, name, price) values (" + product.getId() + ",'"
                        + product.getArcticle() + "','" + product.getName() + "'," + product.getPrice() + ")";
                statement.execute(sqlProducts);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Products update success!");
    }

    public void updateOrders(List<Order> set) {
        try (Statement statement = getConnection().createStatement()) {
            for (Order order : set) {
                String sqlOrders = "insert into orders(id, user_id, product_id) values (" + order.getId() + "," +
                        order.getUserid() + "," + order.getProductId() + ")";
                statement.execute(sqlOrders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Orders update success!");
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
