package ru.croc.task16;

import ru.croc.task16.taxi.TaxiOperations;
import ru.croc.task16.taxi.TaxiPark;
import ru.croc.task16.taxi.models.Client;
import ru.croc.task16.taxi.models.Driver;
import ru.croc.task16.taxi.models.Point;
import java.util.Arrays;
import java.util.Scanner;

public class Task16 {
    public static void main(String[] args) {
        String driversPath = "src/ru/croc/task16/taxi/drivers";
        TaxiOperations taxiOperations = new TaxiOperations();
        TaxiPark taxiPark = new TaxiPark(taxiOperations.taxiDrivers(1, driversPath));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Client data: ");
        String[] clientCoordinates = scanner.nextLine().split(",");
        String clientCarType = scanner.nextLine();
        String[] clientWishes = scanner.nextLine().split(",");
        Client client = new Client(new Point(Double.parseDouble(clientCoordinates[0]),
                Double.parseDouble(clientCoordinates[1])), clientCarType, Arrays.asList(clientWishes));

        Driver nearestDriver = taxiPark.findNearestDriver(client);
        if (nearestDriver.equals(null)) {
            System.out.println("Unfortunately, we could not find a driver with the parameters that suit you!");
        } else {
            System.out.println("Your driver: " + nearestDriver);
        }
    }
}
