package ru.croc.finaltask;

import ru.croc.finaltask.school.School;
import ru.croc.finaltask.school.SchoolDatabaseOperations;
import ru.croc.finaltask.school.objects.User;
import ru.croc.finaltask.school.objects.Word;
import java.util.List;

public class FinalTask {
    public static void main(String[] args) {
        SchoolDatabaseOperations databaseOperations = new SchoolDatabaseOperations("jdbc:mysql://195.133.197.161" +
                ":3306/englishschool", "EnglishSchool", "u3jt4Ya)1m[MI5hw");
        databaseOperations.createTables();

        User curUser = databaseOperations.connectUser();
        databaseOperations.checkUserConnection(curUser);
        if (curUser != null) {
            List<Word> userWords = databaseOperations.getUsersWords(curUser.getUsername());
            for (Word word : userWords) {
                System.out.println(word);
            }
            School school = new School(curUser, userWords);
            databaseOperations.workWithWords(curUser);
        }
        databaseOperations.closeConnection();
    }
}