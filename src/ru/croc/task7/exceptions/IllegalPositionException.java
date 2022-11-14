package ru.croc.task7.exceptions;

// Exception for checking figure positions
public class IllegalPositionException extends RuntimeException {
    public IllegalPositionException(char x, char y) {
        super("Incorrect chessboard position: (" + x + "; " + y + ")");
    }

    public IllegalPositionException(char cord) {
        super("Incorrect chessboard position! Error in coordinate: " + cord);
    }
}
