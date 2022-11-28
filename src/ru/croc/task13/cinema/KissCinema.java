package ru.croc.task13.cinema;

import java.util.*;

// Realization of our cinema
public class KissCinema {
    private String filmsFile;
    private String filmsViewsData;
    private List<Film> films;
    private List<User> users;

    public KissCinema(String filmsFile, String filmsViewsData) {
        this.filmsFile = filmsFile;
        this.filmsViewsData = filmsViewsData;
        KissCinemaReader kissCinemaReader = new KissCinemaReader();
        this.films = kissCinemaReader.cinemaFilms(filmsFile);
        this.users = kissCinemaReader.viewedFilms(filmsViewsData);
    }

    // Recommendation searcher
    public String findRecommendationForUser(User user) {
        Map<String, Integer> countUniqueMovies = new HashMap<>();
        for (Film film : getFilms()) {
            if (!user.getFilmViews().contains(film.getFilmId())) {
                countUniqueMovies.put(film.getFilmName(), 0);
            }
        }
        int repeatedFilms = user.getFilmViews().size() / 2;
        for (User u : getUsers()) {
            int repeatedFilmsUser = 0;
            if (u.getUserId() != user.getUserId()) {
                for (Integer film : u.getFilmViews()) {
                    if (user.getFilmViews().contains(film)) {
                        repeatedFilmsUser += 1;
                    }
                }
                if (repeatedFilms <= repeatedFilmsUser) {
                    for (Integer film : u.getFilmViews()) {
                        if (!user.getFilmViews().contains(film)) {
                            countUniqueMovies.computeIfPresent(getFilms().get(film-1).getFilmName(),
                                    (key, value) -> value + 1);
                        }
                    }
                }
            }
        }
        return Collections.max(countUniqueMovies.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    public String getFilmsFile() {
        return filmsFile;
    }

    public void setFilmsFile(String filmsFile) {
        this.filmsFile = filmsFile;
    }

    public String getFilmsViewsData() {
        return filmsViewsData;
    }

    public void setFilmsViewsData(String filmsViewsData) {
        this.filmsViewsData = filmsViewsData;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
