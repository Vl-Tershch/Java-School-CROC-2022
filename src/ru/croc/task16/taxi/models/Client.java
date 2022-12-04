package ru.croc.task16.taxi.models;

import java.util.ArrayList;
import java.util.List;

// Client of TaxiPark
public class Client {
    private Point coordinates;
    private String carClass;
    private List<String> wishes;

    public Client(Point coordinates, String carClass, List<String> wishes) {
        this.coordinates = coordinates;
        this.carClass = carClass;
        this.wishes = wishes;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public List<String> getWishes() {
        return new ArrayList<>(this.wishes);
    }

    public void setWishes(List<String> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "coordinates=" + coordinates +
                ", carClass='" + carClass + '\'' +
                ", wishes=" + wishes +
                '}';
    }
}
