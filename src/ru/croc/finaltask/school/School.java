package ru.croc.finaltask.school;

import ru.croc.finaltask.school.objects.User;
import ru.croc.finaltask.school.objects.Word;


import java.util.List;

public class School {
    private User user;
    private List<Word> userWords;

    public School(User user, List<Word> userWords) {
        this.user = user;
        this.userWords = userWords;
    }
}
