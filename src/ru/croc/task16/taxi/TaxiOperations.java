package ru.croc.task16.taxi;

import ru.croc.task16.taxi.models.Driver;
import ru.croc.task16.taxi.models.Point;
import ru.croc.task16.taxi.readers.TaxiFileDataReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Main taxi operations
public final class TaxiOperations {
    public List<Driver> createTaxiDriversFromFile(String drivers) {
        TaxiFileDataReader taxiFileReader = new TaxiFileDataReader();
        String rawDriversData = taxiFileReader.readData(drivers);

        List<Driver> allDrivers = new ArrayList<>();
        String[] lines = rawDriversData.split("\\r?\\n");
        for (String curLine : lines) {
            String[] newDriver = curLine.split(",");
            allDrivers.add(new Driver(newDriver[0], newDriver[1], newDriver[2], newDriver[3],
                    new Point(Double.parseDouble(newDriver[4]), Double.parseDouble(newDriver[5])),
                    Stream.of(Arrays.copyOfRange(newDriver, 6, newDriver.length)).collect(Collectors.toSet())));
        }
        return allDrivers;
    }

}
