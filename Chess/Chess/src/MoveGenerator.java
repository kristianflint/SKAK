
import java.util.ArrayList;
import java.util.Stack;

public class MoveGenerator {

    /**
     * Generates moves on a Board Object
     *
     * 1 = White Pawn(s) 2 = White Rook(s) 3 = White Knight(s) 4 = White
     * Bishop(s) 5 = White Queeen 6 = White King 7 left blank as pivot with
     * white below, black above 8 = Black Pawn(s) 9 = Black Rook(s) 10 = Black
     * Knight(s) 11 = Black Bishop(s) 12 = Black Queen 13 = Black King
     */
    int[] knightMoves = {0x21, // two up one right
        0x1F, // two up one left
        0x12, // one up two right
        0x0E, // one up two left
        -0x21, // two down one left
        -0x1F, // two down one right
        -0x12, // one down two left
        -0x0E};  // one down two right

    int[] kingMoves = {0x01, // one right
        -0x0F, // one down one right
        -0x10, // one down
        -0x11, // one down one left
        -0x01, // one left
        0x0F, // one up one left
        0x10, // one up
        0x11};     // one up one right
    
    int BLACK_QUEEN = 0x0F;
    int BLACK_ROOK = 0x0E;
    int BLACK_BISHOP = 0x0D;
    int BLACK_KNIGHT = 0x0A;
    
    
    // er de samme som kongens bare med voksende offset :-D 
    int[] queenMoves = {0x01, // one right
        -0x0F, // one down one right
        -0x10, // one down
        -0x11, // one down one left
        -0x01, // one left
        0x0F, // one up one left
        0x10, // one up
        0x11};     // one up one right

    int[] pawnMoves = {0x10, // one up
        0x11, // one up one right
        0x0F, // one up one left
        0x20};     // two up

    int[] bishopMoves = {-0x0F, // one down one right
        -0x11, // one down one left
        0x0F, // one up one left
        0x11};     // one up one right};

    int[] rookMoves = {0x01, // one right
        -0x10, // one down
        -0x01, // one left
        0x10};    // one up

    int[][] slidingMoves = {queenMoves,bishopMoves,rookMoves,knightMoves};
    
    int COMP_COLOR = 1; //0 = white 1 = black
        
    public boolean isValidMove(Board board, int currentPos, int offset, Piece me, boolean isFirst) {
        int lastMove = currentPos - offset;
        //System.out.println( "isValidMove");
        if (!isFirst){
             
            if (((lastMove) & 0x88) == 0) { // inside board
                if((board.board[lastMove] != 0) && (me.getPosition() == lastMove) ){// der stod en på sidste move
                    System.out.println( "me.getPosition()" + me.getPosition());
                    System.out.println( "lastMove" + lastMove);
                    return false;
                }
            }
        }
        //System.out.println( "isValidMove");
         if (((currentPos + offset) & 0x88) == 0) { // inside board

            if ((board.board[(currentPos + offset)] & 0x8) != 0) { // det næste move er en af vores egne
                return false;
            }
        
             return true;
         }
        
         return false;

        
        //return ((currentPos + offset) & 0x88) == 0;
    }
    
    
    public int[] convertPiceTypeToIntList(int type) {
        if (type == BLACK_QUEEN){
            return slidingMoves[0];
        }
         if (type == BLACK_BISHOP){
            return slidingMoves[1];
        }
         if (type == BLACK_ROOK){
            return slidingMoves[2];
        }
         if (type == BLACK_KNIGHT){
            return slidingMoves[3];
        }         
         
        return slidingMoves[3];
        //return ((currentPos + offset) & 0x88) == 0;
    }
    
    //Funktion som også tjekker i forhold til andres brikker
    //  Den funktion skal også tage højde for en passant (hvis nu Boardet kan huske om det var der)

    //funktion som tjekker den er indenfor pladen
    //tjek om den slår sin egen brik.
    //Tjek udenfor
    // KOM SÅ GITHUBBBB
    
    
    public Stack<Move> generateMovesSlidingPeaceRec(Board board, boolean player, Piece CurrPeace, int offset, boolean isFirst) {
        Stack<Move> moves = new Stack<>();
      
                int currentPos = CurrPeace.getPosition();
                Piece tempPeace = new Piece(CurrPeace.type,CurrPeace.position);
                if (isValidMove(board, currentPos, offset, tempPeace, isFirst)) {
                    int newpos = currentPos+offset;
                    //System.out.println( "Newpos:" + Integer.toHexString(newpos));
                    moves.add(new Move(currentPos, newpos,CurrPeace));
                    tempPeace.setPosition(newpos);
                    moves.addAll(generateMovesSlidingPeaceRec(board, player,tempPeace, offset, false));
                }
        return moves;
    }
    
    public Stack<Move> generateMovesSlidingPeace(Board board, boolean player) {
        Stack<Move> moves = new Stack<>();
        
        for (int index = 0; index < board.PiecesWhite.size(); index++) {
            Piece currentPiece = board.PiecesWhite.get(index);
            for (Integer offset : convertPiceTypeToIntList(currentPiece.type)) {
                if ((currentPiece.getType() & 0x4) != 0) { // Is sliding pice
                    moves.addAll(generateMovesSlidingPeaceRec(board, player,currentPiece, offset, true));
                }else{
                    //moves = generateMovesSingle(board, player,currentPiece, offset, true);
                }
                
            }  
        }
        return moves; //return moves;
        
    }
    
    
    public Stack<Move> generateMoves(Board board, boolean player) {
        Stack<Move> moves = new Stack<>();
        /**
         * Calculate Moves Pieces Implemented so far: NONE DONE :X
         */

        // if white : 
        if (player) {
        
        for (int index = 0; index < board.PiecesWhite.size(); index++) {
            Piece currentPiece = board.PiecesWhite.get(index);
            int currentType = board.PiecesWhite.get(index).getType();
            // Generates White King Moves
            if (currentType == 6) { // (12|6)) { // if its a king
                int currentPos = board.PiecesWhite.get(index).getPosition();
                for (Integer offset : kingMoves) {
                    //if (isValidMove(board, currentPos, offset, true)) { // Piece is inside the board!
                        int nextPosContains = board.board[(currentPos + offset)];
                        int nextPos = currentPos + offset;
                        if (nextPosContains < 7) {
                            System.out.println("hej");
                            /** Own piece do nothing */ } 
                        else if (nextPosContains > 7) {
                            System.out.print("hejhej");
                            Piece pieceToCapture = null;
                            for (int a=0;a<board.PiecesBlack.size();a++){
                                    if (board.PiecesBlack.get(a).position == nextPos){
                                        System.out.println(" inside generate captureMove");
                                        pieceToCapture = board.PiecesBlack.get(a);
                                        a=100; // break for loop
                                        }
                                }
                            moves.add(new CaptureMove(currentPos, nextPos, currentPiece, pieceToCapture));
                            }
                        // lav castling move her :O
                        else {
                            System.out.println("hejhejhej");
                            moves.add(new Move(currentPos, nextPos, currentPiece)); }
                    }
                //}
            }
        }
        }
        // al den samme logik bare for sort
        else {}
        return moves;
    }

    
           
public static void main (String[] args) {
System.out.println( "WHAT!?");
Stack<Move> moves = new Stack();
        
MoveGenerator tester = new MoveGenerator();
Board myBoard = new Board();
Move tempMove;

moves = tester.generateMovesSlidingPeace(myBoard, true);


while (moves.size() > 0){
  tempMove = moves.pop();
  System.out.println(Integer.toHexString(tempMove.getPositionTo()));  
}
}

}
