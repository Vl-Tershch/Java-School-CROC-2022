package ru.croc.task16.taxi.models;

import java.util.ArrayList;
import java.util.List;

// Driver in TaxiPark
public class Driver {
    private String fio;
    private String carClass;
    private String carNumber;
    private String telephoneNumber;
    private Point coordinates;
    private List<String> wishes;

    public Driver(String fio, String carClass, String carNumber,
                  String telephoneNumber, Point coordinates, List<String> wishes) {
        this.fio = fio;
        this.carClass = carClass;
        this.carNumber = carNumber;
        this.telephoneNumber = telephoneNumber;
        this.coordinates = coordinates;
        this.wishes = wishes;
    }

    public double calculateDistanceTo(Client client) {
        return Math.sqrt(Math.pow((this.getCoordinates().getX() - client.getCoordinates().getX()), 2) +
                Math.pow((this.getCoordinates().getY() - client.getCoordinates().getY()), 2));
    }

    public String getFio() {
        return this.fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getCarClass() {
        return this.carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Point getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getWishes() {
        return new ArrayList<>(this.wishes);
    }

    public void setWishes(List<String> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        return  fio + " - carNumber:" + carNumber + " - telephoneNumber:" + telephoneNumber;
    }
}
