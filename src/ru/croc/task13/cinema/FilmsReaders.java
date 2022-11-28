package ru.croc.task13.cinema;

import java.util.List;

public interface FilmsReaders<T> {
    /**
     * From the given file extract data
     *
     * @param fileName path to file with data
     */

    String extractData(String fileName);

    /**
     * From the given file extract all string and create list of films objects.
     *
     */
    List<T> cinemaFilms(String fileFilms);

    /**
     * From the given file extract all string and return array with viewed films.
     *
     */
    List<T> viewedFilms(String fileViews);
}
