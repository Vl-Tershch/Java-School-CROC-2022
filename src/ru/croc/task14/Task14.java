package ru.croc.task14;

import ru.croc.task14.filter.BlackListFilter;
import ru.croc.task14.filter.CanDeleteHateComments;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class Task14 {
    public static void main(String[] args) {
        // Set of ban words
        Set<String> animalsBanwordsSet = new HashSet<>(){
            {
                add("rat");
                add("rats");
                add("cat");
                add("cats");
                add("dog");
                add("dogs");
                add("mouse");
                add("mouses");
                add("horse");
                add("horses");
            }
        };

        // List of comment for deleting
        List<String> commentsDelList = new ArrayList<>() {
            {
                add("Welcome to the hackathon!");
                add("OMG amazing rat !!!");
                add("This cat is so nice :)");
                add("I want buy this horse (");
                add("Mouse ... i hate mouse");
                add("Buy me a coffe");
            }
        };

        System.out.println("---------------DELETING HATE COMMENTS----------------");
        System.out.println("[1] Comments before deleting strings with ban words:");
        for (String str : commentsDelList) {
            System.out.println(str);
        }
        CanDeleteHateComments canDeleteHateComments = new CanDeleteHateComments();
        List<String> firstDelRezults = canDeleteHateComments.deleteHateComments(commentsDelList, animalsBanwordsSet);
        System.out.println("\n[2] Comments after deleting strings with ban words:");
        for (String str : firstDelRezults) {
            System.out.println(str);
        }
        System.out.println("\n");

        System.out.println("---------------DELETING COMMENTS CONTAINING CHAR '!'----------------");
        System.out.println("[1] Comments before deleting strings with '!' char:");
        for (String str : commentsDelList) {
            System.out.println(str);
        }

        Predicate<String> containsLetterA = str -> str.contains("!");
        BlackListFilter<String> blackListFilter = new BlackListFilter<>() {};
        List<String> secondDelRezults = (List<String>) blackListFilter.filterComments(commentsDelList, containsLetterA);
        System.out.println("\n[2] Comments after deleting strings with '!' char:");
        for (String str : secondDelRezults) {
            System.out.println(str);
        }
    }
}
