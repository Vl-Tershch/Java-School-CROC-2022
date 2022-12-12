package ru.croc.task17;

import ru.croc.task17.shop.ShopDatabaseLogic;
import java.sql.*;

public class Task17 {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "")) {
            ShopDatabaseLogic shopDatabaseLogic = new ShopDatabaseLogic(con);
            shopDatabaseLogic.createTables();
            shopDatabaseLogic.updateTables(args[0]);
            System.out.println("[RESULT]: Successful added data to database!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
