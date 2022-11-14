package ru.croc.task6.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Class for removing comments in Java
public class CommentRemover {

    // Deleting comments from a String by regex
    public static String removeJavaCommentsStringRegex(String fileCode) {
        return fileCode.replaceAll("//.*|/\\*(?s:.*?)\\*/", "");
    }

    // Deleting comments from a file
    public static String removeJavaCommentsFile(String fileName) throws IOException {
        String allText = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String curLine = bufferedReader.readLine();

            while (curLine != null) {
                stringBuilder.append(curLine);
                stringBuilder.append(System.lineSeparator());
                curLine = bufferedReader.readLine();
            }
            allText = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert allText != null;
        return(removeJavaCommentsStringRegex(allText));
    }
}
