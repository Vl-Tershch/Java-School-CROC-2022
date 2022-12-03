package ru.croc.task15.utils.comparators;

import ru.croc.task15.utils.User;

import java.util.Comparator;

// Comparator for correct User adding
public class UserCompare implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.getAge() == o2.getAge()) {
            return o1.getFio().compareTo(o2.getFio());
        }
        return o2.getAge() - o1.getAge();
    }
}
