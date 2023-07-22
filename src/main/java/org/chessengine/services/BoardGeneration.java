package org.chessengine.services;

import org.chessengine.models.ChessboardState;

public class BoardGeneration {
    public static void implementFen(String fenString) {
        ChessboardState.WP=0; ChessboardState.WN=0; ChessboardState.WB=0;
        ChessboardState.WR=0; ChessboardState.WQ=0; ChessboardState.WK=0;
        ChessboardState.BP=0; ChessboardState.BN=0; ChessboardState.BB=0;
        ChessboardState.BR=0; ChessboardState.BQ=0; ChessboardState.BK=0;
        ChessboardState.CWK=false; ChessboardState.CWQ=false;
        ChessboardState.CBK=false; ChessboardState.CBQ=false;
        int charIndex = 0;
        int boardIndex = 0;
        while (fenString.charAt(charIndex) != ' '){
            switch (fenString.charAt(charIndex++)){
                case 'P': ChessboardState.WP |= (1L << boardIndex++);
                    break;
                case 'p': ChessboardState.BP |= (1L << boardIndex++);
                    break;
                case 'N': ChessboardState.WN |= (1L << boardIndex++);
                    break;
                case 'n': ChessboardState.BN |= (1L << boardIndex++);
                    break;
                case 'B': ChessboardState.WB |= (1L << boardIndex++);
                    break;
                case 'b': ChessboardState.BB |= (1L << boardIndex++);
                    break;
                case 'R': ChessboardState.WR |= (1L << boardIndex++);
                    break;
                case 'r': ChessboardState.BR |= (1L << boardIndex++);
                    break;
                case 'Q': ChessboardState.WQ |= (1L << boardIndex++);
                    break;
                case 'q': ChessboardState.BQ |= (1L << boardIndex++);
                    break;
                case 'K': ChessboardState.WK |= (1L << boardIndex++);
                    break;
                case 'k': ChessboardState.BK |= (1L << boardIndex++);
                    break;
                case '/':
                    break;
                case '1': boardIndex++;
                    break;
                case '2': boardIndex += 2;
                    break;
                case '3': boardIndex += 3;
                    break;
                case '4': boardIndex += 4;
                    break;
                case '5': boardIndex += 5;
                    break;
                case '6': boardIndex += 6;
                    break;
                case '7': boardIndex += 7;
                    break;
                case '8': boardIndex += 8;
                    break;
                default:
                    break;
            }
        }
        ChessboardState.WhiteToMove = (fenString.charAt(++charIndex) == 'w');
        charIndex += 2;
        while (fenString.charAt(charIndex) != ' ')
        {
            switch (fenString.charAt(charIndex++))
            {
                case 'K': ChessboardState.CWK = true;
                    break;
                case 'Q': ChessboardState.CWQ = true;
                    break;
                case 'k': ChessboardState.CBK = true;
                    break;
                case 'q': ChessboardState.CBQ = true;
                    break;
                default:
                    break;
            }
        }
        if (fenString.charAt(++charIndex) != '-'){
            ChessboardState.EP = Moves.FileMasks8[fenString.charAt(charIndex++) - 'a'];
        }
    }
}
