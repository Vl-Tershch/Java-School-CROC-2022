package ru.croc.task17.shop.objects;

import java.util.ArrayList;
import java.util.List;

public final class DatabaseData {
    private List<User> users;
    private List<Product> products;
    private List<Order> orders;

    public DatabaseData(List<User> users, List<Product> products, List<Order> orders) {
        this.users = users;
        this.products = products;
        this.orders = orders;
    }

    public List<User> getUsers() {
        return new ArrayList<>(this.users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(this.products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(this.orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
