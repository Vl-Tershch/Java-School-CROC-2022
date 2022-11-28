package ru.croc.task13.cinema;

// Films in our cinema
public class Film {
    private int filmId;
    private String filmName;

    public Film(int filmId, String filmName) {
        if (filmName.isEmpty() || filmId < 0) {
            throw new IllegalArgumentException("Film name can't be empty or id can't be lower 0!");
        }
        this.filmId = filmId;
        this.filmName = filmName;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                '}';
    }
}
