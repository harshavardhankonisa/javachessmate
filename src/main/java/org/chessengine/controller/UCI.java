package org.chessengine.controller;

import org.chessengine.models.ChessboardState;
import org.chessengine.models.Constants;
import org.chessengine.services.BoardGenerator;
import org.chessengine.services.ChessSearch;
import org.chessengine.services.Moves;

import java.util.Scanner;

import static org.chessengine.services.FunctionalConversion.*;

public class UCI {
    public static void uciProtocol() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.nextLine();
            if (command.equals("uci")) {
                handleUciCommand();
            } else if (command.equals("isready")) {
                handleIsReadyCommand();
            } else if (command.startsWith("position")) {
                handlePositionCommand(command);
            } else if (command.startsWith("go")) {
                handleGoCommand();
            } else if (command.equals("exit")) {
                input.close();
                System.exit(0);
            }
        }
    }

    private static void handleUciCommand() {
        System.out.println("id name " + Constants.ENGINE_NAME);
        System.out.println("id author " + Constants.ENGINE_AUTHOR);
        System.out.println("uciok");
    }

    private static void handleIsReadyCommand() {
        System.out.println("readyok");
    }

    private static void handlePositionCommand(String command) {
        command = command.substring(9).concat(" ");
        if (command.contains("startpos ")) {
            command = command.substring(9);
            String defFenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
            BoardGenerator.implementFen(defFenString);
        }

        if (command.contains("moves")) {
            int movesIndex = command.indexOf("moves") + 6;
            command = command.substring(movesIndex);
            while (command.length() > 0) {
                String moves;
                if (ChessboardState.WhiteToMove) {
                    moves = Moves.possibleMovesW(ChessboardState.WP, ChessboardState.WN, ChessboardState.WB,
                            ChessboardState.WR, ChessboardState.WQ, ChessboardState.WK, ChessboardState.BP,
                            ChessboardState.BN, ChessboardState.BB, ChessboardState.BR, ChessboardState.BQ,
                            ChessboardState.BK, ChessboardState.EP, ChessboardState.CWK, ChessboardState.CWQ);
                } else {
                    moves = Moves.possibleMovesB(ChessboardState.WP, ChessboardState.WN, ChessboardState.WB,
                            ChessboardState.WR, ChessboardState.WQ, ChessboardState.WK, ChessboardState.BP,
                            ChessboardState.BN, ChessboardState.BB, ChessboardState.BR, ChessboardState.BQ,
                            ChessboardState.BK, ChessboardState.EP, ChessboardState.CBK, ChessboardState.CBQ);
                }
                convertAlgebraicToMove(command, moves);
                int emptyCharIndex = command.indexOf(" ") + 1;
                command = command.substring(emptyCharIndex);
            }
        }
    }

    private static void handleGoCommand() {
        long startTime = System.currentTimeMillis();
        String bestMove = ChessSearch.search(startTime);
        System.out.println("bestmove " + convertMoveToAlgebra(bestMove));
    }

}
