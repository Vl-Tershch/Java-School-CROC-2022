package ru.croc.task13.cinema;

import java.util.*;

// Realization of our cinema
public class KissCinema {
    private List<Film> films;
    private List<User> users;

    public KissCinema(List<Film> films, List<User> users) {
        this.films = films;
        this.users = users;
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
            List<Integer> repeatedFilmsInds = new ArrayList<>();
            if (u.getUserId() != user.getUserId()) {
                for (Integer film : u.getFilmViews()) {
                    if (user.getFilmViews().contains(film) && !(repeatedFilmsInds.contains(film))) {
                        repeatedFilmsUser += 1;
                        repeatedFilmsInds.add(film);
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
