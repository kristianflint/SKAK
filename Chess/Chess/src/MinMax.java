import java.util.Scanner;
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
            Stack<Move> moves = moveGenerator.generateMoves(node, true, 1);

            for(Move move : moves) { // skal bare ændres til while loop for at implementere alpha beta :D ?
                Board newNode = node.copyMe();
                newNode.movePiece(move);
                int value = minimax(newNode, depth-1, false, start_depth);;
                if (value > alpha){ // If  V > a  let a = V 
                    alpha = value;
                    if(depth == start_depth) {
                        godBoard = newNode.copyMe();
                        godMove.pieceToMove = move.pieceToMove;
                        godMove.positionFrom = move.positionFrom;
                        godMove.positionTo = move.positionTo;
                    }
                } 
                if (beta <= alpha) return alpha;
            } 
            return alpha;
        }
        else {
            Stack<Move> moves = moveGenerator.generateMoves(node, true, 0);
            
            for(Move move : moves) { // skal bare ændres til while loop for at implementere alpha beta :D ?
                Board newNode = node.copyMe();
                newNode.movePiece(move);
                int value = minimax(newNode, depth-1, true, start_depth);
                if (value < beta){ // If  V < b  let b = V
                    beta = value;
                } 
                if (beta <= alpha) return beta;
            }
            return beta;
        }
    }
    
    public static void main (String[] args) {

    Evaluator eval = new Evaluator();
    Board tempBoard = new Board();
    Board undoBoard = new Board();
    Move tempMove;
    Board newBoard = new Board();
    newBoard.populateMe();
    MinMax minmax = new MinMax();
    String i, k;
    int e;
    
    Scanner sc = new Scanner(System.in);
    
    System.out.println("START BOARD");
    newBoard.Print();
    
    tempBoard = newBoard.copyMe();
     // true = white as max, false = black as max
    
    while(true){
     
     System.out.println("Your move:");
     i = sc.nextLine();
     k = sc.nextLine();
     
     
     if(i.equals("999")){ //undo 
         tempBoard = undoBoard.copyMe();
         System.out.println("Current Board:");
         tempBoard.Print();
          System.out.println("Your move:");
          i = sc.nextLine();
          k = sc.nextLine();
          undoBoard = tempBoard.copyMe();
     }
     undoBoard = tempBoard.copyMe();
     tempBoard.movePiece(new Move(Integer.parseInt(i, 16),Integer.parseInt(k, 16), new Piece(1,1)));
     tempBoard.Print();
     
    System.out.println("Computer Moves:");
    minmax.minimax(tempBoard, 4, true, 4);
    tempBoard = minmax.godBoard.copyMe();
    tempBoard.Print();
    
    //e = eval.evaluateBoard(tempBoard, 1);
    //System.out.println("evaluering: " + e);
    
    //System.out.println("GOD MOVE");
    System.out.println("GOD MOVE: from " + Integer.toHexString(minmax.godMove.getPositionFrom()) + " (.)(.) to: " + Integer.toHexString(minmax.godMove.getPositionTo()));  
    

    }
     
    }
}
