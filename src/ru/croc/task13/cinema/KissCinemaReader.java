package ru.croc.task13.cinema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Special reader of data for cinema
public class KissCinemaReader implements FilmsReaders {
    @Override
    public String extractData(String fileName) {
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

    @Override
    public List<Film> cinemaFilms(String fileFilms) {
        String rawFilmsData = extractData(fileFilms);
        List<Film> allFilms = new ArrayList<>();
        String[] lines = rawFilmsData.split("\\r?\\n");
        for (String curLine : lines) {
            String[] newFilm = curLine.split(",");
            allFilms.add(new Film(Integer.parseInt(newFilm[0]), newFilm[1]));
        }
        return allFilms;
    }

    @Override
    public List<User> viewedFilms(String fileViews) {
        String rawViewsData = extractData(fileViews);
        List<User> allUsers = new ArrayList<>();
        String[] lines = rawViewsData.split("\\r?\\n");
        int ind = 0;
        for (String curLine : lines) {
            String[] newViews = curLine.split(",");
            allUsers.add(new User(ind, Arrays.stream(newViews).map(Integer::valueOf).collect(Collectors.toSet())));
            ind += 1;
        }
        return allUsers;
    }
}
