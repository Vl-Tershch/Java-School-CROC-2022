package ru.croc.task13.cinema;

import java.util.Set;

// Users in our cinema
public class User {
    private int userId;
    private Set<Integer> filmViews;

    public User(int userId, Set<Integer> filmViews) {
        this.userId = userId;
        this.filmViews = filmViews;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<Integer> getFilmViews() {
        return filmViews;
    }

    public void setFilmViews(Set<Integer> filmViews) {
        this.filmViews = filmViews;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", filmViews=" + filmViews +
                '}';
    }
}
