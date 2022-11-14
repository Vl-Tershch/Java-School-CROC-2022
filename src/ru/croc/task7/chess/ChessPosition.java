package ru.croc.task7.chess;

import ru.croc.task7.exceptions.IllegalPositionException;
import java.util.Objects;

// A class for modeling the positions of figures on the board
public class ChessPosition {
    private char x;
    private char y;

    public ChessPosition(char x, char y) {
        if (!(checkX(x) && checkY(y))) {
            throw new IllegalPositionException(x, y);
        }
        this.x = x;
        this.y = y;
    }

    public static ChessPosition parse(String position) {
        if (position.length() < 2) {
            throw new IllegalPositionException(position.charAt(0));
        }
        return new ChessPosition(position.charAt(0), position.charAt(1));
    }

    private boolean checkX(char x) {
        return x >= 'a' && x <= 'h';
    }

    private boolean checkY(char y) {
        return y >= '1' && y <= '8';
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
        if (!checkX(x)) {
            throw new IllegalPositionException(x);
        }
        this.x = x;
    }

    public char getY() {
        return y;
    }

    public void setY(char y) {
        if (!checkY(y)) {
            throw new IllegalPositionException(y);
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return String.valueOf(x) + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPosition position)) return false;
        return getX() == position.x && getY() == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
