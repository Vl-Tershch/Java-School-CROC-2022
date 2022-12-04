package ru.croc.task13.cinema;

import java.util.ArrayList;
import java.util.List;

// Users in our cinema
public class User {
    private int userId;
    private List<Integer> filmViews;

    public User(int userId, List<Integer> filmViews) {
        this.userId = userId;
        this.filmViews = filmViews;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getFilmViews() {
        return new ArrayList<>(this.filmViews);
    }

    public void setFilmViews(List<Integer> filmViews) {
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
