package ru.croc.task13;

import ru.croc.task13.cinema.Film;
import ru.croc.task13.cinema.KissCinema;
import ru.croc.task13.cinema.KissCinemaReader;
import ru.croc.task13.cinema.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task13 {
    public static void main(String[] args) {
        String moviesPath = "src/ru/croc/task13/movies";
        String filmsViews = "src/ru/croc/task13/filmsviewsTest";
        String myFilmsViews = "src/ru/croc/task13/filmsviews";

        KissCinemaReader kissCinemaReader = new KissCinemaReader();
        KissCinema kissCinema = new KissCinema(kissCinemaReader.cinemaFilms(moviesPath),
                kissCinemaReader.viewedFilms(filmsViews));
        System.out.println("OUR MOVIES: ");
        for (Film film : kissCinema.getFilms()) {
            System.out.println(film);
        }

        System.out.println("\n[1] For test views from task file:");
        User myFirstUser = new User(11, new ArrayList<>(Arrays.asList(2, 4)));
        System.out.println("Recomended film for " + myFirstUser + " - "
                + kissCinema.findRecommendationForUser(myFirstUser));
        System.out.println("----------------");

        System.out.println("[2] For test my views:");
        KissCinema kissCinema2 = new KissCinema(kissCinemaReader.cinemaFilms(moviesPath),
                kissCinemaReader.viewedFilms(myFilmsViews));
        User mySecondUser = new User(12, new ArrayList<>(Arrays.asList(10, 12)));
        System.out.println("Recomended film for " + mySecondUser + " - "
                + kissCinema2.findRecommendationForUser(mySecondUser));
        System.out.println("----------------");

        System.out.println("[3] For test command line input for my views:");
        Scanner scanner = new Scanner(System.in);
        String[] watchedFilms = scanner.nextLine().split(",");
        User myThirdUser = new User(13, Arrays.stream(watchedFilms).map(Integer::valueOf)
                .collect(Collectors.toList()));
        System.out.println("Recomended film for " + myThirdUser + " - "
                + kissCinema2.findRecommendationForUser(myThirdUser));
        System.out.println("----------------");
    }
}
