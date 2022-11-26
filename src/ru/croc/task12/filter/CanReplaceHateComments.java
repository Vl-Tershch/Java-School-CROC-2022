package ru.croc.task12.filter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class CanReplaceHateComments implements BlackListFilter{

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        int ind = 0;
        for (String comment : comments) {
            // Clean the string
            String[] commentWords = comment.split(" ");
            for (int i = 0; i < commentWords.length; i++) {
                if (blackList.contains(commentWords[i].toLowerCase())) {
                    commentWords[i] = "*".repeat(commentWords[i].length());
                }
            }

            // Rebuilt the string
            StringBuilder refreshComment = new StringBuilder("");
            for (String commentWord : commentWords) {
                refreshComment.append(commentWord);
                refreshComment.append(' ');
            }
            comments.set(ind, refreshComment.toString());
            ind += 1;
        }
    }
}
