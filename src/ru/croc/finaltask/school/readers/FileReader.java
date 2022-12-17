package ru.croc.finaltask.school.readers;

import java.util.List;

public interface FileReader<T> {

    List<T> readWords(String fileName);
}
