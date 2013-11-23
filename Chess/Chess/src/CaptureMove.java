/**
 *
 * @author troelshenningsen
 */
public class CaptureMove extends Move {
    Piece pieceToCapture;
    // skal også tage den brik der fanges
    public CaptureMove(int positionFrom, int positionTo, Piece pieceToMove, Piece pieceToCapture) {
        super(positionFrom, positionTo, pieceToMove);
        this.pieceToCapture = pieceToCapture;
    }

    public Piece getPieceToCapture() {
        return pieceToCapture;
    }
}
