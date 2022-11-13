package ru.croc.task7.chess;

import ru.croc.task7.exceptions.IllegalMoveException;
import ru.croc.task7.exceptions.IllegalPositionException;
import java.util.Objects;

// A class for modeling the positions of figures on the board
public class ChessPosition {
    private char x;
    private char y;

    public ChessPosition(char x, char y) throws IllegalArgumentException {
        if (!(checkX(x) && checkY(y))) {
            throw new IllegalPositionException(x, y);
        }
        this.x = x;
        this.y = y;
    }

    public static ChessPosition parse(String position) throws IllegalArgumentException {
        if (position.length() < 2) {
            throw new IllegalPositionException(position.charAt(0));
        }
        return new ChessPosition(position.charAt(0), position.charAt(1));
    }

    // Modulation of the movement of the figure
    public static void figureMakeMoves(ChessPosition[] allPositions) throws IllegalMoveException {
        for (int i = 0; i < allPositions.length - 1; i++) {
            if (!figureCanMove(allPositions[i], allPositions[i + 1])) {
                throw new IllegalMoveException(allPositions[i].toString(), allPositions[i + 1].toString());
            }
        }
    }

    // Checking the possibility of the move of the figure
    public static boolean figureCanMove(ChessPosition chessPosition1, ChessPosition chessPosition2) {
        if (chessPosition1.equals(chessPosition2)) {
            return true;
        } else {
            int newX = Math.abs(((int)chessPosition1.getX() - 97) - ((int)chessPosition2.getX() - 97));
            int newY = Math.abs(((int)chessPosition1.getY() - 49) - ((int)chessPosition2.getY() - 49));
            return (newX == 2 && newY == 1) || (newX == 1 && newY == 2);
        }
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
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that = (ChessPosition) o;
        return getX() == that.x && getY() == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
