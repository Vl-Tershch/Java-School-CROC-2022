package ru.croc.task7.exceptions;

// Exception for checking figure moves
public class IllegalMoveException extends RuntimeException {
    public IllegalMoveException(String position1, String position2) {
        super("A knight doesn't walk that way: " + position1 + " -> " + position2);
    }
}
