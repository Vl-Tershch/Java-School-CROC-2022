package ru.croc.task15.utils;

import java.util.ArrayList;
import java.util.List;

// Description of age group
public class AgeGroup {
    String ageGroup;
    List<User> users;

    public AgeGroup(String ageGroup, List<User> users) {
        if (ageGroup.isEmpty()) {
            throw new IllegalArgumentException("Incorrect data in AgeGroup");
        }
        this.ageGroup = ageGroup;
        this.users = users;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public List<User> getUsers() {
        return new ArrayList<>(this.users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return  ageGroup + ": " + users;
    }
}
