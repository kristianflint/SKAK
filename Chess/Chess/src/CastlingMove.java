/**
 *
 * @author troelshenningsen
 */
public class CastlingMove extends Move {
    int secondPositionFrom;
    int secondPositionTo;
    Piece secondPieceToMove;
    
    public CastlingMove(int positionFrom, int positionTo, Piece pieceToMove, int secondPositionFrom, int secondPositionTo, Piece secondPieceToMove) {
        super(positionFrom, positionTo, pieceToMove);
        this.secondPositionFrom = secondPositionFrom;
        this.secondPositionTo = secondPositionTo;
        this.secondPieceToMove = secondPieceToMove;
        }

    public int getSecondPositionFrom() {
        return secondPositionFrom;
    }

    public int getSecondPositionTo() {
        return secondPositionTo;
    }

    public Piece getSecondPieceToMove() {
        return secondPieceToMove;
    }
    
    
    
}
