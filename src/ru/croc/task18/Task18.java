package ru.croc.task18;

import ru.croc.task17.shop.ShopDatabaseLogic;
import ru.croc.task17.shop.objects.Product;
import ru.croc.task18.dao.OrderDao;
import ru.croc.task18.dao.ProductDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Task18 {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "")) {
            ShopDatabaseLogic shopDatabaseLogic = new ShopDatabaseLogic(con);
            shopDatabaseLogic.createTables();
            shopDatabaseLogic.updateTables(args[0]);
            System.out.println("[RESULT]: Successful added data to database!");
            shopDatabaseLogic.printDatabase();

            System.out.println("\nTask 18 TESTS:");
            System.out.println("[TRANSACTION]: Testing find product with article T1:");
            ProductDao productDao = new ProductDao("jdbc:h2:mem:~/test", "sa", "");
            System.out.println(productDao.findProduct("Т1"));

            System.out.println("\n[TRANSACTION]: Testing creating product:");
            System.out.println(productDao.createProduct(new Product(9, "Т9", "Камера", 1999)));

            System.out.println("\n[TRANSACTION]: Testing updating product with article T1:");
            System.out.println(productDao.updateProduct(new Product(1, "Т1", "Монитор", 5000)));
            shopDatabaseLogic.printDatabase();
            System.out.println("\n[TRANSACTION]: Testing deleting products with articles T9 & T1:");
            productDao.deleteProduct("Т9");
            productDao.deleteProduct("Т1");
            shopDatabaseLogic.printDatabase();

            System.out.println("\n[TRANSACTION]: Testing orders adding:");
            OrderDao orderDao = new OrderDao("jdbc:h2:mem:~/test", "sa", "");
            List<Product> products = Arrays.asList(
                    new Product(9, "Т9", "Ноутбук", 1234),
                    new Product(10, "Т10", "Телефон", 12345),
                    new Product(11, "Т11", "Наушники", 12346)
            );
            orderDao.createOrder("ольга", products);
            shopDatabaseLogic.printDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
