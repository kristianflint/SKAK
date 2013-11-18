import java.util.Stack;

public class MinMax {
    static MoveGenerator moveGenerator;
    
    public Double minimax(Board node, int depth, boolean maximizingPlayer) { // function minimax(node, depth, maximizingPlayer)
    
        // SKAL ERSTATTES MED EVALUATOR FUNKTIONEN :D:D
        if (depth == 0) return 0.0;// if depth = 0 or node is a terminal node, return the heuristic value of node
        
        if (maximizingPlayer) {
            Double bestValue = Double.NEGATIVE_INFINITY;
            Stack<Move> moves = moveGenerator.generateMoves(node, false);
            for(Move move : moves) { // skal bare ændres til while loop for at implementere alpha beta :D ?
                Board newNode = node;
                newNode.movePiece(move);
                Double value = minimax(newNode, depth-1, false);
                if (value > bestValue) bestValue = value; } // If  V > a  let a = V
            return bestValue; 
            }
        else {
            Double bestValue = Double.POSITIVE_INFINITY;
            Stack<Move> moves = moveGenerator.generateMoves(node, true);
            for(Move move : moves) { // skal bare ændres til while loop for at implementere alpha beta :D ?
                Board newNode = node;
                newNode.movePiece(move);
                Double value = minimax(newNode, depth-1, true);
                if (value < bestValue) bestValue = value; } // If  V < b  let b = V
            return bestValue;
            }
        }
}
