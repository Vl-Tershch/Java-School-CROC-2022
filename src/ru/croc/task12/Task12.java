package ru.croc.task12;

import ru.croc.task12.filter.CanDeleteHateComments;
import ru.croc.task12.filter.CanReplaceHateComments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task12 {
    public static void main(String[] args) throws InterruptedException {
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

        // List of comment for replacing
        List<String> commentsReplList = new ArrayList<>() {
            {
                add("Horses are smart!");
                add("bro it's disguising(");
                add("I love your brothers cats -.- ");
                add("I love programming and books xd");
                add("Mouse ... i hate mouse yes i repeated this line");
                add("But not this)");
            }
        };

        System.out.println("---------------DELETING----------------");
        System.out.println("Comments before deleting ban words:");
        for (String str : commentsDelList) {
            System.out.println(str);
        }
        CanDeleteHateComments canDeleteHateComments = new CanDeleteHateComments();
        canDeleteHateComments.filterComments(commentsDelList, animalsBanwordsSet);
        System.out.println("\nComments after deleting ban words:");
        for (String str : commentsDelList) {
            System.out.println(str);
        }
        System.out.println('\n');

        System.out.println("--------------REPLACING-----------------");
        System.out.println("Comments before deleting ban words:");
        for (String str : commentsReplList) {
            System.out.println(str);
        }
        CanReplaceHateComments canReplaceHateComments = new CanReplaceHateComments();
        canReplaceHateComments.filterComments(commentsReplList, animalsBanwordsSet);
        System.out.println("\nComments after deleting ban words:");
        for (String str : commentsReplList) {
            System.out.println(str);
        }
    }
}
