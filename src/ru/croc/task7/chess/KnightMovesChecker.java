package ru.croc.task7.chess;

import ru.croc.task7.exceptions.IllegalMoveException;

public class KnightMovesChecker {
    // Modulation of the movement of the figure
    public static void knightCanMakeMoves(ChessPosition[] allPositions) throws IllegalMoveException {
        for (int i = 0; i < allPositions.length - 1; i++) {
            if (!knightCanMove(allPositions[i], allPositions[i + 1])) {
                throw new IllegalMoveException(allPositions[i].toString(), allPositions[i + 1].toString());
            }
        }
        System.out.println("OK");
    }

    // Checking the possibility of the move of the figure
    public static boolean knightCanMove(ChessPosition chessPosition1, ChessPosition chessPosition2) {
        if (chessPosition1.equals(chessPosition2)) {
            return true;
        } else {
            int newX = Math.abs(((int)chessPosition1.getX() - 97) - ((int)chessPosition2.getX() - 97));
            int newY = Math.abs(((int)chessPosition1.getY() - 49) - ((int)chessPosition2.getY() - 49));
            return (newX == 2 && newY == 1) || (newX == 1 && newY == 2);
        }
    }
}
