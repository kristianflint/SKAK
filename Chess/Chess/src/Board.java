import java.util.ArrayList;

public class Board{
    
    /**
     * 0x88 Chess Board Representation
     * 
     *var WHITE_PAWN = 0x01;
     *var WHITE_KNIGHT = 0x02;
     *var WHITE_KING = 0x03;
     *var WHITE_BISHOP = 0x05;
     *var WHITE_ROOK = 0x06;
     *var WHITE_QUEEN = 0x07;
      *
     *var BLACK_PAWN = 0x09;
     *var BLACK_KNIGHT = 0x0A;
     *var BLACK_KING = 0x0B;
     *var BLACK_BISHOP = 0x0D;
     *var BLACK_ROOK = 0x0E;
     *var BLACK_QUEEN = 0x0F;
     *
     * 
     * There are also 2 arrays used to identify pieces still on the board
     * Thus making it easier to identify active pieces 
     * That needs to have moves generated for them
     */
    
    int[] board = new int[128];
    ArrayList<Piece> PiecesWhite = new ArrayList();
    ArrayList<Piece> PiecesBlack = new ArrayList();
    
    int BLACK_QUEEN = 0x0F;
    int BLACK_BISHOP = 0x0D;
    int BLACK_KNIGHT = 0x0A;
    int BLACK_PAWN = 0x09;
    int BLACK_ROOK = 0x0E;
    int BLACK_KING = 0x0B;
    
    
    int WHITE_PAWN = 0x01;
    int WHITE_KNIGHT = 0x02;
    int WHITE_KING = 0x03;
    int WHITE_BISHOP = 0x05;
    int WHITE_ROOK = 0x06;
    int WHITE_QUEEN = 0x07;
    
    boolean enPassant;      // der kan vel kun være 1 mulig en passant af gangen?
    int enPassantPosition;
    
    public Board() {
        
        Piece p0 = new Piece(BLACK_QUEEN, 3);
        Piece p1 = new Piece(BLACK_QUEEN, 2);
        Piece p2 = new Piece(BLACK_KNIGHT, 1);
        Piece p3 = new Piece(BLACK_PAWN, 19);
        
        PiecesWhite.add(p0);
        PiecesWhite.add(p1);
        PiecesWhite.add(p2);
        PiecesWhite.add(p3);
        
        board[1] = BLACK_KNIGHT;
        board[2] = BLACK_BISHOP;
        board[3] = BLACK_QUEEN;
        board[19] = BLACK_PAWN;
        
    }
    
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