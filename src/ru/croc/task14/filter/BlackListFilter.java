package ru.croc.task14.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public interface BlackListFilter<T> {

    /**
     * From the given list of comments removes ones
     * that contain words from the black list.
     *
     * @param comments list of comments; every comment
     *                 is a sequence of words, separated
     *                 by spaces, punctuation or line breaks
     * @param codition predicate; condition of filtering
     */
    default Collection<T> filterComments(Iterable<T> comments, Predicate<T> codition) {
        Collection<T> clearedComments = new ArrayList<>();
        for (T comment : comments) {
            if (!codition.test(comment)) {
                clearedComments.add(comment);
            }
        }
        return clearedComments;
    }
}
