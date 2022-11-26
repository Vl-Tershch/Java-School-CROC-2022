package ru.croc.task12.filter;

import java.util.List;
import java.util.Set;

// Replacing forbidden words with asterisks
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
            StringBuilder refreshComment = new StringBuilder();
            for (String commentWord : commentWords) {
                refreshComment.append(commentWord);
                refreshComment.append(' ');
            }
            comments.set(ind, refreshComment.toString());
            ind += 1;
        }
    }
}
