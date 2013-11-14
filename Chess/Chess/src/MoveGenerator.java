import java.util.Stack;

public class MoveGenerator{

    int[] knightMoves={   0x21,    // two up one right
                          0x1F,    // two up one left
                          0x12,    // one up two right
                          0x0E,    // one up two left
                         -0x21,    // two down one left
                         -0x1F,    // two down one right
                         -0x12,    // one down two left
                         -0x0E };  // one down two right
    
    int[] kingMoves={   0x01,       // one right
                        -0x0F,      // one down one right
                        -0x10,      // one down
                        -0x11,      // one down one left
                        -0x01,      // one left
                        0x0F,       // one up one left
                        0x10,       // one up
                        0x11 };     // one up one right
                    
    // er de samme som kongens bare med voksende offset :-D 
    int[] queenMoves={};
    
    int[] pawnMoves={   0x10,       // one up
                        0x11,       // one up one right
                        0x0F,       // one up one left
                        0x20 };     // two up
                    
    int[] bishopMoves={ -0x0F,      // one down one right
                        -0x11,      // one down one left
                        0x0F,       // one up one left
                        0x11 };     // one up one right};
    
    int[] rookMoves={   0x01,       // one right
                        -0x10,      // one down
                        -0x01,      // one left
                        0x10  };    // one up
        
    public boolean isValidMove(){
    return true;
    }
    //Funktion som også tjekker i forhold til andres brikker
    //funktion som tjekker den er indenfor pladen
    //tjek om den slår sin egen brik.
    
    
    //Tjek udenfor
    
    // KOM SÅ GITHUBBBB
    
     public Stack<Move> generateMoves(Board board, int color) {
        Stack<Move> moves = new Stack<>();
        
        // Calculate Moves
        // skal man måske lave offsets for både hvid og sort? som ligesom vender så de passer? :p
        for(int a=0;a<board.PiecesBlack.size();a++){
        }
        
        return moves;
    }
    

}