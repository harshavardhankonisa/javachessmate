package org.chessengine.services;

import org.chessengine.models.ChessboardState;

public class FunctionalConversion {
    public static void convertAlgebraicToMove(String command, String moves) {
        int start = 0, end = 0;
        int from = (command.charAt(0) - 'a') + (8 * ('8' - command.charAt(1)));
        int to = (command.charAt(2) - 'a') + (8 * ('8' - command.charAt(3)));
        for (int i = 0; i < moves.length(); i += 4) {
            boolean isRegularMove = Character.isDigit(moves.charAt(i + 3));
            int regularStart = (Character.getNumericValue(moves.charAt(i)) * 8) + (Character.getNumericValue(moves.charAt(i + 1)));
            if (isRegularMove) {
                start = regularStart;
                end = (Character.getNumericValue(moves.charAt(i + 2)) * 8) + (Character.getNumericValue(moves.charAt(i + 3)));
            } else if (moves.charAt(i + 3) == 'P') {
                if (Character.isUpperCase(moves.charAt(i + 2))) {
                    start = Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i) - '0'] & Moves.RankMasks8[1]);
                    end = Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i + 1) - '0'] & Moves.RankMasks8[0]);
                } else {
                    start = Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i) - '0'] & Moves.RankMasks8[6]);
                    end = Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i + 1) - '0'] & Moves.RankMasks8[7]);
                }
            } else if (moves.charAt(i + 3) == 'E') {
                if (moves.charAt(i + 2) == 'W') {
                    start = Long
                            .numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i) - '0'] & Moves.RankMasks8[3]);
                    end = Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i + 1) - '0'] & Moves.RankMasks8[2]);
                } else {
                    start = Long
                            .numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i) - '0'] & Moves.RankMasks8[4]);
                    end = Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i + 1) - '0'] & Moves.RankMasks8[5]);
                }
            }
            if ((start == from) && (end == to)) {
                if ((command.charAt(4) == ' ') || (Character.toUpperCase(command.charAt(4)) == Character.toUpperCase(moves.charAt(i + 2)))) {
                    if (isRegularMove) {
                        if (((1L << start) & ChessboardState.WK) != 0) {
                            ChessboardState.CWK = false;
                            ChessboardState.CWQ = false;
                        } else if (((1L << start) & ChessboardState.BK) != 0) {
                            ChessboardState.CBK = false;
                            ChessboardState.CBQ = false;
                        } else if (((1L << start) & ChessboardState.WR & (1L << 63)) != 0) {
                            ChessboardState.CWK = false;
                        } else if (((1L << start) & ChessboardState.WR & (1L << 56)) != 0) {
                            ChessboardState.CWQ = false;
                        } else if (((1L << start) & ChessboardState.BR & (1L << 7)) != 0) {
                            ChessboardState.CBK = false;
                        } else if (((1L << start) & ChessboardState.BR & 1L) != 0) {
                            ChessboardState.CBQ = false;
                        }
                    }
                    ChessboardState.EP = Moves.makeMoveEP(ChessboardState.WP | ChessboardState.BP, moves.substring(i, i + 4));
                    ChessboardState.WR = Moves.makeMoveCastle(ChessboardState.WR, ChessboardState.WK | ChessboardState.BK, moves.substring(i, i + 4), 'R');
                    ChessboardState.BR = Moves.makeMoveCastle(ChessboardState.BR, ChessboardState.WK | ChessboardState.BK, moves.substring(i, i + 4), 'r');
                    ChessboardState.WP = Moves.makeMove(ChessboardState.WP, moves.substring(i, i + 4), 'P');
                    ChessboardState.WN = Moves.makeMove(ChessboardState.WN, moves.substring(i, i + 4), 'N');
                    ChessboardState.WB = Moves.makeMove(ChessboardState.WB, moves.substring(i, i + 4), 'B');
                    ChessboardState.WR = Moves.makeMove(ChessboardState.WR, moves.substring(i, i + 4), 'R');
                    ChessboardState.WQ = Moves.makeMove(ChessboardState.WQ, moves.substring(i, i + 4), 'Q');
                    ChessboardState.WK = Moves.makeMove(ChessboardState.WK, moves.substring(i, i + 4), 'K');
                    ChessboardState.BP = Moves.makeMove(ChessboardState.BP, moves.substring(i, i + 4), 'p');
                    ChessboardState.BN = Moves.makeMove(ChessboardState.BN, moves.substring(i, i + 4), 'n');
                    ChessboardState.BB = Moves.makeMove(ChessboardState.BB, moves.substring(i, i + 4), 'b');
                    ChessboardState.BR = Moves.makeMove(ChessboardState.BR, moves.substring(i, i + 4), 'r');
                    ChessboardState.BQ = Moves.makeMove(ChessboardState.BQ, moves.substring(i, i + 4), 'q');
                    ChessboardState.BK = Moves.makeMove(ChessboardState.BK, moves.substring(i, i + 4), 'k');
                    ChessboardState.WhiteToMove = !ChessboardState.WhiteToMove;
                    break;
                }
            }
        }
    }

    public static String convertMoveToAlgebra(String move) {
        String append = "";
        int start = 0, end = 0;
        if (Character.isDigit(move.charAt(3))) {
            start = (Character.getNumericValue(move.charAt(0)) * 8) + (Character.getNumericValue(move.charAt(1)));
            end = (Character.getNumericValue(move.charAt(2)) * 8) + (Character.getNumericValue(move.charAt(3)));
        } else if (move.charAt(3) == 'P') {
            if (Character.isUpperCase(move.charAt(2))) {
                start = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0) - '0'] & Moves.RankMasks8[1]);
                end = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1) - '0'] & Moves.RankMasks8[0]);
            } else {
                start = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0) - '0'] & Moves.RankMasks8[6]);
                end = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1) - '0'] & Moves.RankMasks8[7]);
            }
            append = String.valueOf(Character.toLowerCase(move.charAt(2)));
        } else if (move.charAt(3) == 'E') {
            if (move.charAt(2) == 'W') {
                start = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0) - '0'] & Moves.RankMasks8[3]);
                end = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1) - '0'] & Moves.RankMasks8[2]);
            } else {
                start = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0) - '0'] & Moves.RankMasks8[4]);
                end = Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1) - '0'] & Moves.RankMasks8[5]);
            }
        }
        String returnMove = "";
        returnMove += (char) ('a' + (start % 8));
        returnMove += (char) ('8' - (start / 8));
        returnMove += (char) ('a' + (end % 8));
        returnMove += (char) ('8' - (end / 8));
        returnMove += append;
        return returnMove;
    }
}