package ru.croc.task19.dao;

import ru.croc.task19.objects.CourierResponse;
import ru.croc.task19.objects.Delivery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourierDao {
    private final String dbPath;
    private final String dbUsername;
    private final String dbPassword;

    public CourierDao(String dbPath, String dbUsername, String dbPassword) {
        this.dbPath = dbPath;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public CourierResponse findOrderByNumber(Integer courierNumber) {
        List<Delivery> deliveriesRes = new ArrayList<>();
        List<String> names = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(this.dbPath, this.dbUsername, this.dbPassword)) {
            try (PreparedStatement courier = con.prepareStatement("select * from couriers where number=?")) {
                courier.setInt(1, courierNumber);
                try (ResultSet resultSet = courier.executeQuery()) {
                    if (resultSet.next()) {
                        int courierId = resultSet.getInt("id");

                        try (PreparedStatement deliveries = con.prepareStatement("select * from deliveries " +
                                "where courier_id=?")) {
                            deliveries.setInt(1, courierId);
                            try (ResultSet resultSetDeliveries = deliveries.executeQuery()) {
                                while (resultSetDeliveries.next()) {
                                    deliveriesRes.add(new Delivery(resultSetDeliveries.getInt("id"),
                                            resultSetDeliveries.getInt("order_id"),
                                            resultSetDeliveries.getInt("courier_id"),
                                            resultSetDeliveries.getString("delivery_time")));
                                }
                            }
                        }
                        for (Delivery delivery : deliveriesRes) {
                            try (PreparedStatement usersNames = con.prepareStatement("select name from users " +
                                    "where id=(select user_id from orders where id=?)")) {
                                usersNames.setInt(1, delivery.getOrderId());
                                try (ResultSet resultNames = usersNames.executeQuery()) {
                                    if (resultNames.next()) {
                                        names.add(resultNames.getString("name"));
                                    } else return null;
                                }
                            }
                        }
                    } else return null;
                }
            }
            return new CourierResponse(deliveriesRes, names);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
