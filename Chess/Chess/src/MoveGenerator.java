public class MoveGenerator{

    int[] knightMoves={   0x21,    // two up one right
                          0x1F,    // two up one left
                          0x12,    // one up two right
                          0x0E,    // one up two left
                         -0x21,    // two down one left
                         -0x1F,    // two down one right
                         -0x12,    // one down two left
                         -0x0E };  // one down two right
    
    int[] kingMoves={};    
    int[] queenMoves={};
    int[] pawnMoves={};
    int[] bishopMoves={};
    int[] rookMoves={};
        
    public boolean isValidMove(){
    return true;
    }
    //Funktion som også tjekker i forhold til andres brikker
    //funktion som tjekker den er indenfor pladen
    //tjek om den slår sin egen brik.
    
    
    //Tjek udenfor
    

}