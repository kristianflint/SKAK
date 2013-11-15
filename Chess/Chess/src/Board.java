import java.util.List;

public class Board{
    
    int[] board = new int[128];
    List<Piece> PiecesWhite;
    List<Piece> PiecesBlack;
    boolean enPassant;
    
    //update board og piece
    public void movePiece(Move move){
        board[move.getPositionTo()] = board[move.getPositionFrom()];
        
        // Der skal tages højde for En passant på en eller anden måde
        
    }
    

    
}