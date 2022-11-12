package ru.croc.task6.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Class for removing comments in Java
public class CommentRemover {

    // Deleting comments from a String
    public String removeJavaCommentsString(String fileCode) {
        ArrayList<String> result = new ArrayList<>();
        String[] fileCodesString = fileCode.split("\n");
        StringBuilder rezString = new StringBuilder();
        boolean inBlock = false;
        String temp = "";

        for (String curLine : fileCodesString) {
            if (!inBlock) {
                temp = "";
            }

            for (int i = 0; i < curLine.length(); i++) {
                if (inBlock) {
                    if (curLine.charAt(i) == '*' && i + 1 < curLine.length() && curLine.charAt(i + 1) == '/') {
                        inBlock = false;
                        i++;
                    }
                } else {
                    if (curLine.charAt(i) == '/' && i + 1 < curLine.length() && curLine.charAt(i + 1) == '/') {
                        if ((i - 1 == -1 || curLine.charAt(i-1) != '"')) {
                            break;
                        }
                    }

                    if (curLine.charAt(i) == '/' && i + 1 < curLine.length() && curLine.charAt(i + 1) == '*') {
                        if ((i - 1 == -1 || curLine.charAt(i-1) != '"')) {
                            inBlock = true;
                            i++;
                            continue;
                        }
                    }
                    temp += curLine.charAt(i);
                }
            }
            if (!inBlock) {
                if (temp.length() > 0) {
                    result.add(temp);
                }
            }
        }
        for (String i : result) {
            rezString.append(i).append("\n");
        }
        return rezString.toString();
    }

    // Deleting comments from a file
    public String removeJavaCommentsFile(String fileName) throws IOException {
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
        return(removeJavaCommentsString(allText));
    }
}
