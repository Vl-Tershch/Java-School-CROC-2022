package ru.croc.task19;

import ru.croc.task17.shop.ShopDatabaseLogic;
import ru.croc.task18.dao.UserDao;
import ru.croc.task19.dao.CourierDao;
import ru.croc.task19.objects.Courier;
import ru.croc.task19.objects.CourierResponse;
import ru.croc.task19.objects.Delivery;
import ru.croc.task19.objects.UserResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Task19 {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "")) {
            ShopDatabaseLogic shopDatabaseLogic = new ShopDatabaseLogic(con);
            shopDatabaseLogic.createTables();
            shopDatabaseLogic.updateTables(args[0]);
            System.out.println("[RESULT]: Successful added data to database!");
            shopDatabaseLogic.printDatabase();

            System.out.println("\nTask 19 TESTS:");
            System.out.println("[TRANSACTION]: Testing adding couriers:");
            List<Courier> couriers = Arrays.asList(
                    new Courier(1, 111, "Олег", "Петров"),
                    new Courier(2, 222, "Иван", "Иванов"),
                    new Courier(3, 333, "Дмитрий", "Сидоров")
            );
            shopDatabaseLogic.updateCouriers(couriers);

            System.out.println("\n[TRANSACTION]: Testing adding deliveries:");
            List<Date> datesTest = Arrays.asList(
                    new Date(),
                    new Date(),
                    new Date(),
                    new Date(),
                    new Date()
            );

            Calendar c = Calendar.getInstance();
            int ind = 1;
            for (int i = 0; i < datesTest.size(); i++) {
                c.setTime(datesTest.get(i));
                c.add(Calendar.DATE, ind);
                ind += 1;
                datesTest.set(i, c.getTime());
            }
            List<Delivery> deliveries = Arrays.asList(
                    new Delivery(1, 1, 1, datesTest.get(0).toString()),
                    new Delivery(2, 13, 1, datesTest.get(1).toString()),
                    new Delivery(3, 2, 2, datesTest.get(2).toString()),
                    new Delivery(4, 4, 3, datesTest.get(3).toString()),
                    new Delivery(5, 9, 2, datesTest.get(4).toString())
            );
            shopDatabaseLogic.updateDeliveries(deliveries);
            shopDatabaseLogic.printDatabase();

            System.out.println("\n[TRANSACTION]: Testing user delivers:");
            UserDao userDao = new UserDao("jdbc:h2:mem:~/test", "sa", "");
            List<UserResponse> userResponses = userDao.findDeliveryDateById(1);
            for (UserResponse userResponse : userResponses) {
                System.out.println(userResponse);
            }

            System.out.println("\n[TRANSACTION]: Testing courier delivers:");
            CourierDao courierDao = new CourierDao("jdbc:h2:mem:~/test", "sa", "");
            CourierResponse courierResponse = courierDao.findOrderByNumber(111);
            List<Delivery> delivers = courierResponse.getDeliveries();
            List<String> names = courierResponse.getNames();
            for (int i = 0; i < courierResponse.getDeliveries().size(); i++) {
                System.out.println(delivers.get(i) + " : "+names.get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
