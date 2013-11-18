import java.util.ArrayList;

public class Board{
    
    /**
     * 0x88 Chess Board Representation
     * 
     * Pieces on the board are represented by integers
     *  1 = White Pawn(s)
     *  2 = White Rook(s)
     *  3 = White Knight(s)
     *  4 = White Bishop(s)
     *  5 = White Queeen
     *  6 = White King
     *  7 = Black Pawn(s)
     *  8 = Black Rook(s)
     *  9 = Black Knight(s)
     * 10 = Black Bishop(s)
     * 11 = Black Queen
     * 12 = Black King
     * 
     * There are also 2 arrays used to identify pieces still on the board
     * Thus making it easier to identify active pieces 
     * That needs to have moves generated for them
     */
    
    int[] board = new int[128];
    ArrayList<Piece> PiecesWhite;
    ArrayList<Piece> PiecesBlack;
    
    boolean enPassant;      // der kan vel kun være 1 mulig en passant af gangen?
    int enPassantPosition;
    
    //update board og piece
    public void movePiece(Move move) {
        board[move.getPositionTo()] = board[move.getPositionFrom()];
        
        // Der skal tages højde for En passant på en eller anden måde
        if (enPassant) removeEnPassant();
        // if you just made en passant enabling move turn it on here
    }
    
    // http://en.wikipedia.org/wiki/Rules_of_chess#Castling
    public void castling(Move moveKing, Move moveCastle){
        board[moveKing.getPositionTo()] = board[moveKing.getPositionFrom()];
        board[moveCastle.getPositionTo()] = board[moveCastle.getPositionFrom()];
        
        if (enPassant) removeEnPassant();
        // if you just made en passant enabling move turn it on here
    }
    
    public boolean enPassantPossible() {
        return enPassant;
    }
    
    public int getEnPassantPositions(){
        return enPassantPosition;
    }
    
    public void setEnPassant(int position){
        enPassantPosition = position;
        enPassant = true;
    }
    
    public void removeEnPassant() {
        enPassant = false;
    }
    
}