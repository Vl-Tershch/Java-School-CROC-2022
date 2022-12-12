package ru.croc.task17.shop.objects;

public class Order {
    Integer id;
    Integer userid;
    Integer productId;

    public Order(Integer id, Integer userid, Integer productId) {
        this.id = id;
        this.userid = userid;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
        if (!userid.equals(order.userid)) return false;
        return productId.equals(order.productId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userid.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userid=" + userid +
                ", productId=" + productId +
                '}';
    }
}
