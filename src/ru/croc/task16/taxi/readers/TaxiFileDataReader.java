package ru.croc.task16.taxi.readers;

import java.io.BufferedReader;
import java.io.IOException;

// Implementation of data reader for drivers data
public class TaxiFileDataReader implements DataReader {
    @Override
    public String readData(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(fileName))) {
            String curLine = bufferedReader.readLine();

            while (curLine != null) {
                stringBuilder.append(curLine);
                stringBuilder.append(System.lineSeparator());
                curLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
