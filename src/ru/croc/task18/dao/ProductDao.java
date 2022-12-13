package ru.croc.task18.dao;

import ru.croc.task17.shop.objects.Product;
import java.sql.*;

public class ProductDao {
    private final String dbPath;
    private final String dbUsername;
    private final String dbPassword;

    public ProductDao(String dbPath, String dbUsername, String dbPassword) {
        this.dbPath = dbPath;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public Product findProduct(String productCode) {
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            try (PreparedStatement product = con.prepareStatement("select * from products where article=?")) {
                product.setString(1, productCode);
                try (ResultSet resultSet = product.executeQuery()) {
                    if (resultSet.next()) {
                        return new Product(resultSet.getInt("id"), resultSet.getString("article"),
                                resultSet.getString("name"), resultSet.getInt("price"));
                    } else return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product createProduct(Product product) {
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            if (findProduct(product.getArcticle()) != null) {
                return null;
            } else {
                try (Statement statement = con.createStatement()) {
                    statement.execute("insert into products(id, article, name, price) values (" + product.getId() +
                            ",'" + product.getArcticle() + "','" + product.getName() + "'," + product.getPrice() + ")");
                }
                return findProduct(product.getArcticle());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product updateProduct(Product product) {
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            if (findProduct(product.getArcticle()) == null) {
                return null;
            } else {
                try (Statement statement = con.createStatement()) {
                    statement.execute("update products set article = '" + product.getArcticle() + "',name = '" +
                            product.getName() + "', price = " + product.getPrice() + "where id =" + product.getId());
                }
                return findProduct(product.getArcticle());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(String productCode) {
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            Product  curProduct = findProduct(productCode);
            if (curProduct == null) {
                System.out.println("This product doesn't exist!");
            } else {
                try (Statement statement = con.createStatement()) {
                    statement.execute("delete from orders where product_id = '" + curProduct.getId() + "'");
                    statement.execute("delete from products where article = '" + productCode + "'");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
