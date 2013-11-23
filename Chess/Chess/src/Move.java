public class Move{
    int positionFrom;
    int positionTo;
    Piece pieceToMove;

    public Move(int positionFrom, int positionTo, Piece pieceToMove) {
        this.positionFrom = positionFrom;
        this.positionTo = positionTo;
        this.pieceToMove = pieceToMove;
    }

    public int getPositionFrom() {
        return positionFrom;
    }

    public int getPositionTo() {
        return positionTo;
    }

    public Piece getPieceToMove() {
        return pieceToMove;
    }
    
}