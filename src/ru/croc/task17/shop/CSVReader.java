package ru.croc.task17.shop;

import ru.croc.task17.shop.objects.Order;
import ru.croc.task17.shop.objects.Product;
import ru.croc.task17.shop.objects.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class CSVReader {
    public static List<List> createLists(String csvPath) {
        List<List> rezultData = new ArrayList<>();
        List<User> userEntries = new ArrayList<>();
        List<Product> productEntries = new ArrayList<>();
        List<Order> orderEntries = new ArrayList<>();
        int userSeq = 1;
        int productSeq = 1;
        int orderSeq = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int existUserSeq = 0;
                int existProductSeq = 0;
                boolean existUser = false;
                boolean existProduct = false;
                String[] values = line.split(",");
                if (userEntries.stream().noneMatch(o -> o.getName().equals(values[1]))) {
                    userEntries.add(new User(userSeq, values[1]));
                    userSeq += 1;
                } else {
                    existUserSeq = userEntries.stream().filter(o -> o.getName().equals(values[1]))
                            .findFirst().get().getId();
                    existUser = true;
                }
                if (productEntries.stream().noneMatch(o -> o.getName().equals(values[3]))) {
                    productEntries.add(new Product(productSeq, values[2], values[3], Integer.parseInt(values[4])));
                    productSeq += 1;
                } else {
                    existProductSeq = productEntries.stream().filter(o -> o.getName().equals(values[3])).findFirst()
                            .get().getId();
                    existProduct = true;
                }

                if (existUser && existProduct) {
                    orderEntries.add(new Order(orderSeq, existUserSeq, existProductSeq));
                } else if (existUser) {
                    orderEntries.add(new Order(orderSeq, existUserSeq, productSeq - 1));
                } else if (existProduct) {
                    orderEntries.add(new Order(orderSeq, userSeq - 1, existProductSeq));
                } else  {
                    orderEntries.add(new Order(orderSeq, userSeq - 1, productSeq - 1));
                }
                orderSeq += 1;
                existUser = false;
                existProduct = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rezultData.add(userEntries);
        rezultData.add(productEntries);
        rezultData.add(orderEntries);
        return rezultData;
    }
}
