package ru.croc.task19.objects;

public class Delivery {
    private Integer id;
    private Integer orderId;
    private Integer courierId;
    private String deliveryTime;

    public Delivery(Integer id, Integer orderId, Integer courierId, String deliveryTime) {
        this.id = id;
        this.orderId = orderId;
        this.courierId = courierId;
        this.deliveryTime = deliveryTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", courierId=" + courierId +
                ", deliveryTime='" + deliveryTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery delivery)) {
            return false;
        }

        if (!id.equals(delivery.id)) return false;
        if (!orderId.equals(delivery.orderId)) return false;
        if (!courierId.equals(delivery.courierId)) return false;
        return deliveryTime.equals(delivery.deliveryTime);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + courierId.hashCode();
        result = 31 * result + deliveryTime.hashCode();
        return result;
    }
}
