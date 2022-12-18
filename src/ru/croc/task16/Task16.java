package ru.croc.task16;

import ru.croc.task16.taxi.TaxiOperations;
import ru.croc.task16.taxi.TaxiPark;
import ru.croc.task16.taxi.models.Client;
import ru.croc.task16.taxi.models.Driver;
import ru.croc.task16.taxi.models.Point;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task16 {
    public static void main(String[] args) {
        String driversPath = "src/ru/croc/task16/taxi/drivers";
        TaxiOperations taxiOperations = new TaxiOperations();
        TaxiPark taxiPark = new TaxiPark(taxiOperations.createTaxiDriversFromFile(driversPath));

        Scanner scanner = new Scanner(System.in);
        String[] clientCoordinates = scanner.nextLine().split(",");
        String clientCarType = scanner.nextLine();
        String[] clientWishes = scanner.nextLine().split(",");
        Client client = new Client(new Point(Double.parseDouble(clientCoordinates[0]),
                Double.parseDouble(clientCoordinates[1])), clientCarType,
                Stream.of(clientWishes).collect(Collectors.toSet()));

        Driver nearestDriver = taxiPark.findNearestDriver(client);
        System.out.println(nearestDriver);
    }
}
