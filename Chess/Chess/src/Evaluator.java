import java.util.ArrayList;


public class Evaluator {

    /**
     *
     * It is better to be wrong by 10 centipawns all the time than to be
     * completely correct 99.9% of the time and wrong by 300 centipawns 0.1% of
     * the time.
     *
     * Material Values:
     * Standard Centipawn values: http://chess.wikia.com/wiki/Centipawn
     * Simplified values: http://chessprogramming.wikispaces.com/Simplified+evaluation+function
     * 
     * Positional Values:
     * Simplified values: http://chessprogramming.wikispaces.com/Simplified+evaluation+function
     */
    
    
    // Piece Types used on the Board
    int WHITE_PAWN = 0x01;
    int WHITE_KNIGHT = 0x02;
    int WHITE_KING = 0x03;
    int WHITE_BISHOP = 0x05;
    int WHITE_ROOK = 0x06;
    int WHITE_QUEEN = 0x07;
    int BLACK_PAWN = 0x09;
    int BLACK_KNIGHT = 0x0A;
    int BLACK_KING = 0x0B;
    int BLACK_BISHOP = 0x0D;
    int BLACK_ROOK = 0x0E;
    int BLACK_QUEEN = 0x0F;
    
    
    // Material Values (from simplified values)
    int kingVal = 20000;
    int queenVal = 900;
    int rookVal = 500;
    int knightVal = 320;
    int bishopVal = 330;
    int pawnVal = 100;

    // White Positional Values (from simplified values)
    int[] wPawnPos = {   0,  0,  0,  0,  0,  0,  0,  0,     0,  0,  0,  0,  0,  0,  0,  0,
                        50, 50, 50, 50, 50, 50, 50, 50,     0,  0,  0,  0,  0,  0,  0,  0,
                        10, 10, 20, 30, 30, 20, 10, 10,     0,  0,  0,  0,  0,  0,  0,  0,
                        5,  5, 10, 25, 25, 10,  5,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        0,  0,  0, 20, 20,  0,  0,  0,      0,  0,  0,  0,  0,  0,  0,  0,
                        5, -5,-10,  0,  0,-10, -5,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        5, 10, 10,-20,-20, 10, 10,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        0,  0,  0,  0,  0,  0,  0,  0,      0,  0,  0,  0,  0,  0,  0,  0};
    
    int wKnightPos[] = { -50,-40,-30,-30,-30,-30,-40,-50,   0,  0,  0,  0,  0,  0,  0,  0,
                        -40,-20,  0,  0,  0,  0,-20,-40,    0,  0,  0,  0,  0,  0,  0,  0,
                        -30,  0, 10, 15, 15, 10,  0,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                        -30,  5, 15, 20, 20, 15,  5,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                        -30,  0, 15, 20, 20, 15,  0,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                        -30,  5, 10, 15, 15, 10,  5,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                        -40,-20,  0,  5,  5,  0,-20,-40,    0,  0,  0,  0,  0,  0,  0,  0,
                        -50,-40,-30,-30,-30,-30,-40,-50,    0,  0,  0,  0,  0,  0,  0,  0};
    
    int wBishopPos[] = { -20,-10,-10,-10,-10,-10,-10,-20,   0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  0,  0,  0,  0,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  5, 10, 10,  5,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  5,  5, 10, 10,  5,  5,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0, 10, 10, 10, 10,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10, 10, 10, 10, 10, 10, 10,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  5,  0,  0,  0,  0,  5,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -20,-10,-10,-10,-10,-10,-10,-20,    0,  0,  0,  0,  0,  0,  0,  0};
    
    int wRookPos[] = {   0,  0,  0,  0,  0,  0,  0,  0,     0,  0,  0,  0,  0,  0,  0,  0,
                        5, 10, 10, 10, 10, 10, 10,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        0,  0,  0,  5,  5,  0,  0,  0,      0,  0,  0,  0,  0,  0,  0,  0};  
    
    int wQueenPos[] = { -20,-10,-10, -5, -5,-10,-10,-20,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  0,  0,  0,  0,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  5,  5,  5,  5,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  5,  5,  5,  5,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        0,  0,  5,  5,  5,  5,  0, -5,      0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  5,  5,  5,  5,  5,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  5,  0,  0,  0,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -20,-10,-10, -5, -5,-10,-10,-20,    0,  0,  0,  0,  0,  0,  0,  0};
    
    int wKingPosMid[] = {    -30,-40,-40,-50,-50,-40,-40,-30,   0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-40,-40,-50,-50,-40,-40,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-40,-40,-50,-50,-40,-40,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-40,-40,-50,-50,-40,-40,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -20,-30,-30,-40,-40,-30,-30,-20,    0,  0,  0,  0,  0,  0,  0,  0,
                            -10,-20,-20,-20,-20,-20,-20,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                            20, 20,  0,  0,  0,  0, 20, 20,     0,  0,  0,  0,  0,  0,  0,  0,
                            20, 30, 10,  0,  0, 10, 30, 20,     0,  0,  0,  0,  0,  0,  0,  0};
    
    int wKingPosEnd[] = {    -50,-40,-30,-20,-20,-30,-40,-50,   0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-20,-10,  0,  0,-10,-20,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 20, 30, 30, 20,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 30, 40, 40, 30,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 30, 40, 40, 30,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 20, 30, 30, 20,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-30,  0,  0,  0,  0,-30,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -50,-30,-30,-30,-30,-30,-30,-50,    0,  0,  0,  0,  0,  0,  0,  0};  
    
    
    // White Positional Values: White Values Reversed!
    int bPawnPos[] = {  0,  0,  0,  0,  0,  0,  0,  0,      0,  0,  0,  0,  0,  0,  0,  0,
                        5, 10, 10,-20,-20, 10, 10,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        5, -5,-10,  0,  0,-10, -5,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        0,  0,  0, 20, 20,  0,  0,  0,      0,  0,  0,  0,  0,  0,  0,  0,
                        5,  5, 10, 25, 25, 10,  5,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        10, 10, 20, 30, 30, 20, 10, 10,     0,  0,  0,  0,  0,  0,  0,  0,
                        50, 50, 50, 50, 50, 50, 50, 50,     0,  0,  0,  0,  0,  0,  0,  0,
                        0,  0,  0,  0,  0,  0,  0,  0,      0,  0,  0,  0,  0,  0,  0,  0};
    
    int bKnightPos[] = { -50,-40,-30,-30,-30,-30,-40,-50,   0,  0,  0,  0,  0,  0,  0,  0,
                         -40,-20,  0,  5,  5,  0,-20,-40,   0,  0,  0,  0,  0,  0,  0,  0,
                         -30,  5, 10, 15, 15, 10,  5,-30,   0,  0,  0,  0,  0,  0,  0,  0,
                         -30,  0, 15, 20, 20, 15,  0,-30,   0,  0,  0,  0,  0,  0,  0,  0,
                         -30,  5, 15, 20, 20, 15,  5,-30,   0,  0,  0,  0,  0,  0,  0,  0,
                         -30,  0, 10, 15, 15, 10,  0,-30,   0,  0,  0,  0,  0,  0,  0,  0,
                         -40,-20,  0,  0,  0,  0,-20,-40,   0,  0,  0,  0,  0,  0,  0,  0,
                         -50,-40,-30,-30,-30,-30,-40,-50,   0,  0,  0,  0,  0,  0,  0,  0};
    
    int bBishopPos[] = { -20,-10,-10,-10,-10,-10,-10,-20,   0,  0,  0,  0,  0,  0,  0,  0,
                         -10,  5,  0,  0,  0,  0,  5,-10,   0,  0,  0,  0,  0,  0,  0,  0,
                         -10, 10, 10, 10, 10, 10, 10,-10,   0,  0,  0,  0,  0,  0,  0,  0,
                         -10,  0, 10, 10, 10, 10,  0,-10,   0,  0,  0,  0,  0,  0,  0,  0,
                         -10,  5,  5, 10, 10,  5,  5,-10,   0,  0,  0,  0,  0,  0,  0,  0,
                         -10,  0,  5, 10, 10,  5,  0,-10,   0,  0,  0,  0,  0,  0,  0,  0,
                         -10,  0,  0,  0,  0,  0,  0,-10,   0,  0,  0,  0,  0,  0,  0,  0,
                         -20,-10,-10,-10,-10,-10,-10,-20,   0,  0,  0,  0,  0,  0,  0,  0};
    
    int bRookPos[] = {   0,  0,  0,  0,  0,  0,  0,  0,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        -5,  0,  0,  0,  0,  0,  0, -5,     0,  0,  0,  0,  0,  0,  0,  0,
                        5, 10, 10, 10, 10, 10, 10,  5,      0,  0,  0,  0,  0,  0,  0,  0,
                        0,  0,  0,  5,  5,  0,  0,  0,      0,  0,  0,  0,  0,  0,  0,  0};  
    
    int bQueenPos[] = { -20,-10,-10, -5, -5,-10,-10,-20,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  5,  0,  0,  0,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  5,  5,  5,  5,  5,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                          0,  0,  5,  5,  5,  5,  0, -5,    0,  0,  0,  0,  0,  0,  0,  0,
                         -5,  0,  5,  5,  5,  5,  0, -5,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  5,  5,  5,  5,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -10,  0,  0,  0,  0,  0,  0,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                        -20,-10,-10, -5, -5,-10,-10,-20,    0,  0,  0,  0,  0,  0,  0,  0};  
    
    int bKingPosMid[] = {   20, 30, 10,  0,  0, 10, 30, 20,     0,  0,  0,  0,  0,  0,  0,  0,
                            20, 20,  0,  0,  0,  0, 20, 20,     0,  0,  0,  0,  0,  0,  0,  0,
                            -10,-20,-20,-20,-20,-20,-20,-10,    0,  0,  0,  0,  0,  0,  0,  0,
                            -20,-30,-30,-40,-40,-30,-30,-20,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-40,-40,-50,-50,-40,-40,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-40,-40,-50,-50,-40,-40,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-40,-40,-50,-50,-40,-40,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-40,-40,-50,-50,-40,-40,-30,    0,  0,  0,  0,  0,  0,  0,  0}; 

    int bKingPosEnd[] = {   -50,-30,-30,-30,-30,-30,-30,-50,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-30,  0,  0,  0,  0,-30,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 20, 30, 30, 20,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 30, 40, 40, 30,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 30, 40, 40, 30,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-10, 20, 30, 30, 20,-10,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -30,-20,-10,  0,  0,-10,-20,-30,    0,  0,  0,  0,  0,  0,  0,  0,
                            -50,-40,-30,-20,-20,-30,-40,-50,    0,  0,  0,  0,  0,  0,  0,  0};
    

    public int evaluateBoard(Board board, int turn) {
        int blackValue = 0;
        int whiteValue = 0;
        boolean endGame; // false still midgame, true now in endgame
        
        //identify game state (Mid / End)
        endGame = identifyGameState(board.PiecesBlack,board.PiecesWhite);
        if (endGame) System.out.println("\nendgame reached\n");
        
        // sums up the material and positional values for black
        for (Piece pc : board.PiecesBlack){
            int type = pc.getType();
            int position = pc.getPosition();
            if (type == BLACK_PAWN)         blackValue += (pawnVal+bPawnPos[position]);
            else if (type == BLACK_KNIGHT)  blackValue += (knightVal+bKnightPos[position]);
            else if (type == BLACK_BISHOP)  blackValue += (bishopVal+bBishopPos[position]);
            else if (type == BLACK_ROOK)    blackValue += (rookVal+bRookPos[position]);
            else if (type == BLACK_QUEEN)   blackValue += (queenVal+bQueenPos[position]);
            else if (type == BLACK_KING) {
                    if (!endGame)       blackValue += (kingVal+bKingPosMid[position]);
                    else if (endGame)   blackValue += (kingVal+bKingPosEnd[position]);
            }    
        }
        
        // sums up the material and positional values for white
        for (Piece pc : board.PiecesWhite){
            int type = pc.getType();
            int position = pc.getPosition();
            if (type == WHITE_PAWN)         whiteValue += (pawnVal+wPawnPos[position]);
            else if (type == WHITE_KNIGHT)  whiteValue += (knightVal+wKnightPos[position]);
            else if (type == WHITE_BISHOP)  whiteValue += (bishopVal+wBishopPos[position]);
            else if (type == WHITE_ROOK)    whiteValue += (rookVal+wRookPos[position]);
            else if (type == WHITE_QUEEN)   whiteValue += (queenVal+wQueenPos[position]);
            else if (type == WHITE_KING) {
                    if (!endGame)       whiteValue += (kingVal+wKingPosMid[position]);
                    else if (endGame)   whiteValue += (kingVal+wKingPosEnd[position]);
            }
        }
        
        // positive value is in favor of white negative is in favor of black
        /**
        if (turn==0){
            
            System.out.println(turn);
            System.out.println("Boardscore: " + (whiteValue-blackValue));
            board.Print();
            System.out.println("------------------------------");
            
            return (whiteValue-blackValue);
        }
        else{
            System.out.println(turn);
            System.out.println("Boardscore: " + (blackValue-whiteValue));
            board.Print();
            System.out.println("------------------------------");
            
            return (blackValue-whiteValue);
        }*/
        /*
            System.out.println(turn);
            System.out.print("white pieces: ");
            for (Piece pc : board.PiecesWhite){System.out.print(pc.type + "@" +pc.position + " ");}
            System.out.print("\nblack pieces: ");
            for (Piece pc : board.PiecesBlack){System.out.print(pc.type + "@" +pc.position + "  ");}
            System.out.println("");
            System.out.println("Boardscore: " + (whiteValue-blackValue));
            board.Print();
            System.out.println("------------------------------");
            */
            return (whiteValue-blackValue);
        
    }
    
    private boolean identifyGameState(ArrayList<Piece> blackPieces, ArrayList<Piece> whitePieces) {
        /** 
         * Both sides have no queens or Side which has a queen 
         * has additionally no other pieces or one minorpiece maximum.
         */
        
        boolean state;
        int bqueen = 0;
        int bminor = 0;
        int wqueen = 0;
        int wminor = 0;
        
        for (Piece curP : blackPieces) {
            int type = curP.getType();
            if (type == BLACK_QUEEN) bqueen++;
            else if (type == BLACK_KING) ;// do nothing
            else bminor++;
        }
        
        for (Piece curP : whitePieces) {
            int type = curP.getType();
            if (type == WHITE_QUEEN) wqueen++;
            else if (type == WHITE_KING) ;// do nothing
            else wminor++;
        }
        
        if (bqueen == 0 && wqueen == 0) state = true;
        else if (bqueen == 1 && bminor < 2 && wqueen == 0) state = true;
        else if (bqueen == 0 && wminor < 2 && wqueen == 1) state = true;
        else state = false; // still in midgame?
            
        return state;
    }   
}
