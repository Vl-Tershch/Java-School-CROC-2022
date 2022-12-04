package ru.croc.task16.taxi;

import ru.croc.task16.taxi.models.Driver;
import ru.croc.task16.taxi.models.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TaxiOperations {
    public String readFileDriversData(String fileName) {
        String allText = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String curLine = bufferedReader.readLine();

            while (curLine != null) {
                stringBuilder.append(curLine);
                stringBuilder.append(System.lineSeparator());
                curLine = bufferedReader.readLine();
            }
            allText = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allText;
    }

    public List<Driver> taxiDrivers(int type, String drivers) {
        String rawDriversData;
        if (type == 1) {
            rawDriversData = readFileDriversData(drivers);
        } else {
            rawDriversData = drivers;
        }
        List<Driver> allDrivers = new ArrayList<>();
        String[] lines = rawDriversData.split("\\r?\\n");
        for (String curLine : lines) {
            String[] newDriver = curLine.split(",");
            allDrivers.add(new Driver(newDriver[0], newDriver[1], newDriver[2], newDriver[3],
                    new Point(Double.parseDouble(newDriver[4]), Double.parseDouble(newDriver[5])),
                    new ArrayList<>(Arrays.asList(Arrays.copyOfRange(newDriver, 6, newDriver.length)))));
        }
        return allDrivers;
    }

}
