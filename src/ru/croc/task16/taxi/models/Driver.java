package ru.croc.task16.taxi.models;

import ru.croc.task16.taxi.enums.CarClass;
import ru.croc.task16.taxi.enums.CarWish;
import java.util.HashSet;
import java.util.Set;

// Driver in TaxiPark
public class Driver {
    private final String fio;
    private final CarClass carClass;
    private final String carNumber;
    private final String telephoneNumber;
    private final Point coordinates;
    private final Set<CarWish> wishes;

    public Driver(String fio, String carClass, String carNumber,
                  String telephoneNumber, Point coordinates, Set<String> wishes) {
        Set<CarWish> carWishes = new HashSet<>();
        this.fio = fio;
        this.carClass = CarClass.get(carClass);
        this.carNumber = carNumber;
        this.telephoneNumber = telephoneNumber;
        this.coordinates = coordinates;

        for (String wish : wishes) {
            carWishes.add(CarWish.get(wish));
        }
        this.wishes = new HashSet<>(carWishes);
    }

    public double calculateDistanceTo(Client client) {
        return Math.sqrt(Math.pow((this.getCoordinates().getX() - client.getCoordinates().getX()), 2) +
                Math.pow((this.getCoordinates().getY() - client.getCoordinates().getY()), 2));
    }

    public String getFio() {
        return this.fio;
    }

    public CarClass getCarClass() {
        return this.carClass;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public Point getCoordinates() {
        return this.coordinates;
    }

    public Set<CarWish> getWishes() {
        return this.wishes;
    }

    @Override
    public String toString() {
        return  "U-" +fio +  "-" + telephoneNumber;
    }
}
