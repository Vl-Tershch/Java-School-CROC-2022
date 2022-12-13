package ru.croc.task17.shop.objects;

public class Order {
    Integer id;
    Integer userId;
    Integer productId;

    public Order(Integer id, Integer userId, Integer productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) {
            return false;
        }

        if (!id.equals(order.id)) return false;
        if (!userId.equals(order.userId)) return false;
        return productId.equals(order.productId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userid=" + userId +
                ", productId=" + productId +
                '}';
    }
}
