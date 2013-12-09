import java.util.Stack;

public class MinMax {
    static MoveGenerator moveGenerator = new MoveGenerator();
    static Evaluator eval = new Evaluator();
    Board godBoard = new Board();
    Piece notToUse = new Piece(1,1);
    Move godMove = new Move(1,1,notToUse);
    
    public int minimax(Board node, int depth, boolean maximizingPlayer, int start_depth) { // function minimax(node, depth, maximizingPlayer)
        
        int turn;
        if (maximizingPlayer) turn = 0;
        else turn = 1;
        int alpha = -999999999;
        int beta = 999999999;

        
        if (depth == 0) return (eval.evaluateBoard(node, turn));// if depth = 0 or node is a terminal node, return the heuristic value of node
        
        if (maximizingPlayer) {
            Stack<Move> moves = moveGenerator.generateMoves(node, true, 0);

            for(Move move : moves) { // skal bare ændres til while loop for at implementere alpha beta :D ?
                Board newNode = node.copyMe();
                newNode.movePiece(move);
                int value = minimax(newNode, depth-1, false,start_depth);
                if (value > alpha){ // If  V > a  let a = V 
                    alpha = value;
                    if(depth == start_depth) {
                        godBoard = newNode.copyMe();
                        godMove.pieceToMove = move.pieceToMove;
                        godMove.positionFrom = move.positionFrom;
                        godMove.positionTo = move.positionTo;
                    }
                } 
                if (beta <= alpha) break;
            } 
            return alpha;
        }
        else {
            Stack<Move> moves = moveGenerator.generateMoves(node, true, 1);
            
            for(Move move : moves) { // skal bare ændres til while loop for at implementere alpha beta :D ?
                Board newNode = node.copyMe();
                newNode.movePiece(move);
                int value = minimax(newNode, depth-1, true, start_depth);
                if (value < beta){ // If  V < b  let b = V
                    beta = value;
                } 
                if (beta <= beta) break;
            }
            return beta;
        }
    }
    
    public static void main (String[] args) {

    Board newBoard = new Board();
    newBoard.populateMe();
    MinMax minmax = new MinMax();
    System.out.println("START BOARD");
    newBoard.Print();
    minmax.minimax(newBoard, 6, true, 6); // true = white as max, false = black as max
    System.out.println("GOD BOARD");
    minmax.godBoard.Print();
    System.out.println("GOD MOVE");
   
    System.out.println("from " + Integer.toHexString(minmax.godMove.getPositionFrom()) + " (.)(.) to: " + Integer.toHexString(minmax.godMove.getPositionTo()));  
        
    }
}
