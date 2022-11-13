package ru.croc.task7;

import ru.croc.task7.chess.ChessPosition;
import ru.croc.task7.exceptions.IllegalMoveException;
import ru.croc.task7.exceptions.IllegalPositionException;

public class Task7 {
    public static void main(String[] args) {
        try {
            ChessPosition[] positions = new ChessPosition[args.length];
            for (int i = 0; i < args.length; i++) {
                positions[i] = ChessPosition.parse(args[i]);
            }
            ChessPosition.figureMakeMoves(positions);
            System.out.println("OK");

        } catch (IllegalPositionException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
    }
}
