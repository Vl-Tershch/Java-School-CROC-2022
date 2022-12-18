package ru.croc.task16.taxi.models;

import ru.croc.task16.taxi.enums.CarClass;
import ru.croc.task16.taxi.enums.CarWish;
import java.util.HashSet;
import java.util.Set;

// Client of TaxiPark
public class Client {
    private final Point coordinates;
    private final CarClass carClass;
    private final Set<CarWish> wishes;

    public Client(Point coordinates, String carClass, Set<String> wishes) {
        Set<CarWish> carWishes = new HashSet<>();
        this.coordinates = coordinates;
        this.carClass = CarClass.get(carClass);

        for (String wish : wishes) {
            carWishes.add(CarWish.get(wish));
        }
        this.wishes = carWishes;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public Set<CarWish> getWishes() {
        return this.wishes;
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
