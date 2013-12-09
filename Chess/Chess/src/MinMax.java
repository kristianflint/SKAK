import java.util.Stack;

public class MinMax {
    static MoveGenerator moveGenerator = new MoveGenerator();
    static Evaluator eval = new Evaluator();
    
    public int minimax(Board node, int depth, boolean maximizingPlayer) { // function minimax(node, depth, maximizingPlayer)
        
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
                int value = minimax(newNode, depth-1, false);
                if (value > alpha) alpha = value; // If  V > a  let a = V 
                if (beta <= alpha) break;
            } 
            return alpha;
        }
        else {
            Stack<Move> moves = moveGenerator.generateMoves(node, true, 1);
            
            for(Move move : moves) { // skal bare ændres til while loop for at implementere alpha beta :D ?
                Board newNode = node.copyMe();
                newNode.movePiece(move);
                int value = minimax(newNode, depth-1, true);
                if (value < beta) beta = value; // If  V < b  let b = V
                if (beta <= beta) break;
            }
            return beta;
        }
    }
    
    public static void main (String[] args) {

    Board newBoard = new Board();
    newBoard.populateMe();
    MinMax minmax = new MinMax();
    minmax.minimax(newBoard, 6, true); // true = white as max, false = black as max
    }
}
