package ru.croc.task6;

import ru.croc.task6.util.CommentRemover;
import java.io.IOException;

public class Task6 {
    public static void main(String[] args) {
        CommentRemover commentRemover = new CommentRemover();
        try {
            System.out.println(commentRemover.removeJavaCommentsFile("/Users/vladtereshch/IdeaProjects/Java-School-2022/src/ru/croc/task6/test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String code = "/*\n" +
                " * My first ever program in Java!\n" +
                " */\n" +
                "class Hello { // class body starts here\n" +
                "// Modification\n" +
                "  /* main method */\n" +
                "  public static void main(String[] args/* we put command line arguments here*/) {\n" +
                "    // this line prints my first greeting to the screen\n" +
                "    fileCode.replaceAll(\"//.*|/\\\\*(?s:.*?)\\\\*/\", \"\");\n" +
                "// Modification 2\n" +
                "    System.out.println(\"Hi!\"); // :)\n" +
                "    Sytstem.out.println(\"/*/\");\n" +
                "    Sytstem.out.println('/');\n" +
                "  }\n" +
                "} // the end\n" +
                "// to be continued...";
        System.out.println(commentRemover.removeJavaCommentsString(code));
    }
}
