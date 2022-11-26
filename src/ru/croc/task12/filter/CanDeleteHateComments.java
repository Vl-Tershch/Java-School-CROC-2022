package ru.croc.task12.filter;

import java.util.*;
import java.util.function.Function;
import static java.util.stream.Collectors.*;

public class CanDeleteHateComments implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        int ind = 0;
        for (String comment : comments) {
            // Clean the string
            String[] commentWords = comment.split(" ");
            Map<String, Long> currentWords = Arrays.stream(commentWords).
                    collect(groupingBy(Function.identity(),counting()));
            for (int i = 0; i < commentWords.length; i++) {
                if (blackList.contains(commentWords[i].toLowerCase())) {
                    commentWords[i] = "";
                }
            }

            // Rebuilt the string
            StringBuilder refreshComment = new StringBuilder("");
            for (String commentWord : commentWords) {
                if (currentWords.containsKey(commentWord)) {
                    refreshComment.append(commentWord);
                    refreshComment.append(' ');
                }
            }
            comments.set(ind, refreshComment.toString());
            ind += 1;
        }
    }
}
