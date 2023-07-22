package org.chessengine.services;

import org.chessengine.models.ChessboardState;

public class BoardEvaluator {
    static int[][] pawnBoard = {
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 50, 50, 50, 50, 50, 50, 50, 50 },
            { 10, 10, 20, 30, 30, 20, 10, 10 },
            { 5, 5, 10, 25, 25, 10, 5, 5 },
            { 0, 0, 0, 20, 20, 0, 0, 0 },
            { 5, -5, -10, 0, 0, -10, -5, 5 },
            { 5, 10, 10, -20, -20, 10, 10, 5 },
            { 0, 0, 0, 0, 0, 0, 0, 0 } 
        };
    static int[][] rookBoard = {
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 5, 10, 10, 10, 10, 10, 10, 5 },
            { -5, 0, 0, 0, 0, 0, 0, -5 },
            { -5, 0, 0, 0, 0, 0, 0, -5 },
            { -5, 0, 0, 0, 0, 0, 0, -5 },
            { -5, 0, 0, 0, 0, 0, 0, -5 },
            { -5, 0, 0, 0, 0, 0, 0, -5 },
            { 0, 0, 0, 5, 5, 0, 0, 0 } };
    static int[][] knightBoard = {
            { -50, -40, -30, -30, -30, -30, -40, -50 },
            { -40, -20, 0, 0, 0, 0, -20, -40 },
            { -30, 0, 10, 15, 15, 10, 0, -30 },
            { -30, 5, 15, 20, 20, 15, 5, -30 },
            { -30, 0, 15, 20, 20, 15, 0, -30 },
            { -30, 5, 10, 15, 15, 10, 5, -30 },
            { -40, -20, 0, 5, 5, 0, -20, -40 },
            { -50, -40, -30, -30, -30, -30, -40, -50 } };
    static int[][] bishopBoard = {
            { -20, -10, -10, -10, -10, -10, -10, -20 },
            { -10, 0, 0, 0, 0, 0, 0, -10 },
            { -10, 0, 5, 10, 10, 5, 0, -10 },
            { -10, 5, 5, 10, 10, 5, 5, -10 },
            { -10, 0, 10, 10, 10, 10, 0, -10 },
            { -10, 10, 10, 10, 10, 10, 10, -10 },
            { -10, 5, 0, 0, 0, 0, 5, -10 },
            { -20, -10, -10, -10, -10, -10, -10, -20 } };
    static int[][] queenBoard = {
            { -20, -10, -10, -5, -5, -10, -10, -20 },
            { -10, 0, 0, 0, 0, 0, 0, -10 },
            { -10, 0, 5, 5, 5, 5, 0, -10 },
            { -5, 0, 5, 5, 5, 5, 0, -5 },
            { 0, 0, 5, 5, 5, 5, 0, -5 },
            { -10, 5, 5, 5, 5, 5, 0, -10 },
            { -10, 0, 5, 0, 0, 0, 0, -10 },
            { -20, -10, -10, -5, -5, -10, -10, -20 } };

    public static int boardEvaluation(int checkScore, long WP, long WN, long WB, long WR, long WQ, long BP, long BN, long BB, long BR, long BQ) {
        int score = 0;
        score += evaluatePieces(WP, WN, WB, WR, WQ, BP, BN, BB, BR, BQ);
        score += evaluatePosition(WP, WN, WB, WR, WQ, BP, BN, BB, BR, BQ);
        score += checkScore;
        return score;
    }

    public static int evaluatePieces(long WP, long WN, long WB, long WR, long WQ, long BP, long BN, long BB, long BR, long BQ) {
        int score = 0;
        int WBishopCount = 0;
        int BBishopCount = 0;
        for (int i = 0; i < 64; i++) {
            if (((WP >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + 100 : score - 100;
            }
            if (((WN >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + 300 : score - 300;
            }
            if (((WB >> i) & 1) == 1) {
                WBishopCount = WBishopCount + 1;
            }
            if (((WR >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + 500 : score - 500;
            }
            if (((WQ >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + 900 : score - 900;
            }
            if (((BP >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - 100 : score + 100;
            }
            if (((BN >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - 300 : score + 300;
            }
            if (((BB >> i) & 1) == 1) {
                BBishopCount = BBishopCount + 1;
            }
            if (((BR >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - 500 : score + 500;
            }
            if (((BQ >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - 900 : score + 900;
            }
        }
        if (WBishopCount == 2) {
            score = ChessboardState.WhiteToMove ? score + (350 * WBishopCount) : score - (350 * WBishopCount);
        } else {
            if (WBishopCount == 1) {
                score = ChessboardState.WhiteToMove ? score + 250 : score - 250;
            }
        }
        if (BBishopCount == 2) {
            score = ChessboardState.WhiteToMove ? score - (350 * WBishopCount) : score + (350 * WBishopCount);
        } else {
            if (BBishopCount == 1) {
                score = ChessboardState.WhiteToMove ? score - 250 : score + 250;
            }
        }
        return score;
    }

    public static int evaluatePosition(long WP, long WN, long WB, long WR, long WQ, long BP, long BN, long BB, long BR, long BQ) {
        int score = 0;
        for (int i = 0; i < 64; i++) {
            if (((WP >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + pawnBoard[i / 8][i % 8] : score - pawnBoard[i / 8][i % 8];
            }
            if (((WN >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + knightBoard[i / 8][i % 8]
                        : score - knightBoard[i / 8][i % 8];
            }
            if (((WB >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + bishopBoard[i / 8][i % 8]
                        : score - bishopBoard[i / 8][i % 8];
            }
            if (((WR >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + rookBoard[i / 8][i % 8] : score - rookBoard[i / 8][i % 8];
            }
            if (((WQ >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score + queenBoard[i / 8][i % 8]
                        : score - queenBoard[i / 8][i % 8];
            }
            if (((BP >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - pawnBoard[i / 8][i % 8] : score + pawnBoard[i / 8][i % 8];
            }
            if (((BN >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - knightBoard[i / 8][i % 8]
                        : score + knightBoard[i / 8][i % 8];
            }
            if (((BB >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - bishopBoard[i / 8][i % 8]
                        : score + bishopBoard[i / 8][i % 8];
            }
            if (((BR >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - rookBoard[i / 8][i % 8] : score + rookBoard[i / 8][i % 8];
            }
            if (((BQ >> i) & 1) == 1) {
                score = ChessboardState.WhiteToMove ? score - queenBoard[i / 8][i % 8]
                        : score + queenBoard[i / 8][i % 8];
            }
        }
        return score;
    }
}
