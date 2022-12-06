package ru.croc.task14.filter;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

// Delete all hate string comments
public final class CanDeleteHateComments {
    public List<String> deleteHateComments(List<String> comments, Set<String> blackList) {
        BlackListFilter<String> filter = new BlackListFilter<>() {};

        Predicate<String> predicate = comment -> {
            String[] words = comment.toLowerCase().split(" ");
            for (String word : words) {
                if (blackList.contains(word)) {
                    return true;
                }
            }
            return false;
        };
        return (List<String>)filter.filterComments(comments, predicate);
    }
}
