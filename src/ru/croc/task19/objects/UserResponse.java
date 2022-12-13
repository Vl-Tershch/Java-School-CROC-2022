package ru.croc.task19.objects;

public class UserResponse {
    private String dateDelivery;
    private String nameCourier;

    public UserResponse(String dateDelivery, String nameCourier) {
        this.dateDelivery = dateDelivery;
        this.nameCourier = nameCourier;
    }

    public String getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public String getNameCourier() {
        return nameCourier;
    }

    public void setNameCourier(String nameCourier) {
        this.nameCourier = nameCourier;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "dateDelivery='" + dateDelivery + '\'' +
                ", nameCourier='" + nameCourier + '\'' +
                '}';
    }
}
