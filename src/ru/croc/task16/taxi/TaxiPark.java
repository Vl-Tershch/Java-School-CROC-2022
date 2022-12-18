package ru.croc.task16.taxi;

import ru.croc.task16.taxi.models.Client;
import ru.croc.task16.taxi.models.Driver;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Our taxi park with drivers
public class TaxiPark {
    private final List<Driver> taxiDrivers;

    public TaxiPark(List<Driver> taxiDrivers) {
        if (taxiDrivers.isEmpty()) {
            throw new IllegalArgumentException("Drivers data can't be empty!");
        }
        this.taxiDrivers = taxiDrivers;
    }

    public Driver findNearestDriver(Client client) {
        ArrayList<Driver> allDrivers = new ArrayList<>(getTaxiDrivers());
        allDrivers.sort(Comparator.comparingDouble(d -> d.calculateDistanceTo(client)));
        for (Driver driver : allDrivers) {
            if (driver.getCarClass().equals(client.getCarClass())) {
                long countWishes = driver.getWishes().stream().filter(carWish ->
                        client.getWishes().contains((carWish))).count();
                if (countWishes == client.getWishes().size()) {
                    return driver;
                }
            }
        }
        return null;
    }

    public List<Driver> getTaxiDrivers() {
        return new ArrayList<>(this.taxiDrivers);
    }

    @Override
    public String toString() {
        return "TaxiPark{" +
                "taxiDrivers=" + taxiDrivers +
                '}';
    }
}
