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
    
    public int returnPiecesSize(int color) {  //0 = white 1 = black
             
        switch (color) {
            case 0:  
                return PiecesWhite.size();
            case 1:  
                return PiecesBlack.size();              
        }
        return 0;
    }
      public Piece getPiece(int index, int color) {  //0 = white 1 = black
             
        switch (color) {
            case 0:  
                return PiecesWhite.get(index);
            case 1:  
                return PiecesBlack.get(index);              
        }
        return PiecesBlack.get(0); //need better error check, return emtpy piece
    }      
    public Board() {
        String hex = "ff";
          
        Piece p0 = new Piece(BLACK_QUEEN, Integer.parseInt("3", 16));
        Piece p1 = new Piece(BLACK_BISHOP, Integer.parseInt("2", 16));
        Piece p2 = new Piece(BLACK_KNIGHT, Integer.parseInt("1", 16));
        Piece p11 = new Piece(BLACK_ROOK, Integer.parseInt("0", 16));
        
        Piece p12 = new Piece(BLACK_KING, Integer.parseInt("4", 16));
        Piece p13 = new Piece(BLACK_BISHOP, Integer.parseInt("5", 16));
        Piece p14 = new Piece(BLACK_KNIGHT, Integer.parseInt("6", 16));
        Piece p15 = new Piece(BLACK_ROOK, Integer.parseInt("7", 16));     
        
        Piece p3 = new Piece(BLACK_PAWN, Integer.parseInt("10", 16));
        Piece p4 = new Piece(BLACK_PAWN, Integer.parseInt("11", 16));
        Piece p5 = new Piece(BLACK_PAWN, Integer.parseInt("12", 16));
        Piece p6 = new Piece(BLACK_PAWN, Integer.parseInt("13", 16));
        Piece p7 = new Piece(BLACK_PAWN, Integer.parseInt("14", 16));
        Piece p8 = new Piece(BLACK_PAWN, Integer.parseInt("15", 16));
        Piece p9 = new Piece(BLACK_PAWN, Integer.parseInt("16", 16));
        Piece p10 = new Piece(BLACK_PAWN, Integer.parseInt("17", 16));
        
        board[Integer.parseInt("0", 16)] = BLACK_ROOK;
        board[Integer.parseInt("1", 16)] = BLACK_KNIGHT;
        board[Integer.parseInt("2", 16)] = BLACK_BISHOP;
        board[Integer.parseInt("3", 16)] = BLACK_QUEEN;

        board[Integer.parseInt("4", 16)] = BLACK_KING;
        board[Integer.parseInt("5", 16)] = BLACK_BISHOP;
        board[Integer.parseInt("6", 16)] = BLACK_KNIGHT;
        board[Integer.parseInt("7", 16)] = BLACK_ROOK;
        
        board[Integer.parseInt("10", 16)] = BLACK_PAWN;
        board[Integer.parseInt("11", 16)] = BLACK_PAWN;
        board[Integer.parseInt("12", 16)] = BLACK_PAWN;
        board[Integer.parseInt("13", 16)] = BLACK_PAWN;
        board[Integer.parseInt("14", 16)] = BLACK_PAWN;
        board[Integer.parseInt("15", 16)] = BLACK_PAWN;
        board[Integer.parseInt("16", 16)] = BLACK_PAWN;
        board[Integer.parseInt("17", 16)] = BLACK_PAWN;        
        
        
        PiecesBlack.add(p0);
        PiecesBlack.add(p1);
        PiecesBlack.add(p2);
        PiecesBlack.add(p3);
        PiecesBlack.add(p4);
        PiecesBlack.add(p5);
        PiecesBlack.add(p6);
        PiecesBlack.add(p7);
        PiecesBlack.add(p8);
        PiecesBlack.add(p9);
        PiecesBlack.add(p10);
        PiecesBlack.add(p11);
        PiecesBlack.add(p12);
        PiecesBlack.add(p13);
        PiecesBlack.add(p14);
        PiecesBlack.add(p15);
        
        
        
        //WHITE
          
        Piece p21 = new Piece(WHITE_QUEEN, Integer.parseInt("74", 16));
        Piece p22 = new Piece(WHITE_BISHOP, Integer.parseInt("72", 16));
        Piece p23 = new Piece(WHITE_KNIGHT, Integer.parseInt("71", 16));
        Piece p24 = new Piece(WHITE_ROOK, Integer.parseInt("70", 16));
        
        Piece p25 = new Piece(WHITE_KING, Integer.parseInt("73", 16));
        Piece p26 = new Piece(WHITE_BISHOP, Integer.parseInt("75", 16));
        Piece p27 = new Piece(WHITE_KNIGHT, Integer.parseInt("76", 16));
        Piece p28 = new Piece(WHITE_ROOK, Integer.parseInt("77", 16));     
        
        Piece p29 = new Piece(WHITE_PAWN, Integer.parseInt("60", 16));
        Piece p30 = new Piece(WHITE_PAWN, Integer.parseInt("61", 16));
        Piece p31 = new Piece(WHITE_PAWN, Integer.parseInt("62", 16));
        Piece p32 = new Piece(WHITE_PAWN, Integer.parseInt("63", 16));
        Piece p33 = new Piece(WHITE_PAWN, Integer.parseInt("64", 16));
        Piece p34 = new Piece(WHITE_PAWN, Integer.parseInt("65", 16));
        Piece p35 = new Piece(WHITE_PAWN, Integer.parseInt("66", 16));
        Piece p36 = new Piece(WHITE_PAWN, Integer.parseInt("67", 16));
            
        board[Integer.parseInt("70", 16)] = WHITE_ROOK;
        board[Integer.parseInt("71", 16)] = WHITE_KNIGHT;
        board[Integer.parseInt("72", 16)] = WHITE_BISHOP;
        board[Integer.parseInt("73", 16)] = WHITE_QUEEN;
        board[Integer.parseInt("74", 16)] = WHITE_KING;
        board[Integer.parseInt("75", 16)] = WHITE_BISHOP;
        board[Integer.parseInt("76", 16)] = WHITE_KNIGHT;
        board[Integer.parseInt("77", 16)] = WHITE_ROOK;     
        board[Integer.parseInt("60", 16)] = WHITE_PAWN;
        board[Integer.parseInt("61", 16)] = WHITE_PAWN;
        board[Integer.parseInt("62", 16)] = WHITE_PAWN;
        board[Integer.parseInt("63", 16)] = WHITE_PAWN;
        board[Integer.parseInt("64", 16)] = WHITE_PAWN;
        board[Integer.parseInt("65", 16)] = WHITE_PAWN;
        board[Integer.parseInt("66", 16)] = WHITE_PAWN;
        board[Integer.parseInt("67", 16)] = WHITE_PAWN;        
        
        
        PiecesWhite.add(p21);
        PiecesWhite.add(p22);
        PiecesWhite.add(p23);
        PiecesWhite.add(p24);
        PiecesWhite.add(p25);
        PiecesWhite.add(p26);
        PiecesWhite.add(p27);
        PiecesWhite.add(p28);
        PiecesWhite.add(p29);
        PiecesWhite.add(p30);
        PiecesWhite.add(p31);
        PiecesWhite.add(p32);
        PiecesWhite.add(p33);
        PiecesWhite.add(p34);
        PiecesWhite.add(p35);
        PiecesWhite.add(p36);
        
        
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