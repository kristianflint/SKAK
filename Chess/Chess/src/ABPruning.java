
import java.util.Stack;

   
public class ABPruning
{
  int alpha;
  int beta;  
  private final int inf = 999999999;
//  Stack moves = new Stack();
//  Stack childrenStack = new Stack();
//  Board board = new Board();
 // Board newBoard = new Board();
  private int score;
  private int best_score;
  private Evaluator myEvaluator = new Evaluator();
  private MoveGenerator myMoveGenerator = new MoveGenerator();
  private Board GODboard = new Board();
         
public static void main (String[] args) {

  Board newBoard = new Board();
  newBoard.populateMe();
  ABPruning newABPruning = new ABPruning();
  newABPruning.pruning(newBoard);
    
}

  public void pruning(Board board){
      
      miniMax(2, 1, board); 
      System.out.println("GOD BOARD");
      GODboard.Print();
  
  }
  
  public int miniMax(int depth, int turn, Board board)  
        {  
            boolean endOfGAME = false;
            
            Stack<Move> moves = new Stack<>();
            if (endOfGAME){
                  switch (4){
                   case 1:  return inf;
                   case 2:  return -inf;
                   case 3:  return 0;                    
                }
            }
            if (depth == 0){
                return myEvaluator.evaluateBoard(board, turn);
            }
            
            moves = myMoveGenerator.generateMoves(board, true, turn);

                
                for (Move move : moves) {
                    best_score = inf;        
                    Board newBoard = board.copyMe();
                    newBoard.movePiece((Move)move);
                    newBoard.Print();
                    if (turn == 1) score = alphaBetaMax(alpha, beta, depth-1, turn, newBoard, depth);                               
                    else score = alphaBetaMin(alpha, beta, depth-1, turn, newBoard);                               
                }    
            
             
            return score;
 }
                           
public int alphaBetaMax( int alpha, int beta, int depth, int turn, Board board, int startDepth) {
   Stack moves = new Stack();
   
   if (depth == 0){
        return myEvaluator.evaluateBoard(board, turn);
   }
   
   turn=(turn==1)?0:1;
   moves = myMoveGenerator.generateMoves(board, true, turn);
   
   
    for (Object move : moves) {
        Board newBoard = board.copyMe();
        newBoard.movePiece((Move)move);
        score = alphaBetaMin( alpha, beta, depth - 1, turn, newBoard);
      if( score >= beta )
        return beta;
      if( score > alpha ){
        alpha = score; 
        if(startDepth == depth) {
            Board GODboard = newBoard.copyMe();
        }
      }
   }
   return alpha;
}
 
public int alphaBetaMin( int alpha, int beta, int depth, int turn, Board board) {  
   Stack<Move> moves = new Stack<>();
   
   if (depth == 0){
    return myEvaluator.evaluateBoard(board, turn);
   }
   
   turn=(turn==1)?0:1;
   moves = myMoveGenerator.generateMoves(board, true, turn);
   
    for (Move move : moves) {
        Board newBoard = board.copyMe();
        newBoard.movePiece((Move)move);
       score = alphaBetaMax( alpha, beta, depth - 1, turn, newBoard, depth);
      if( score <= alpha )
         return alpha;
      if( score < beta )
         beta = score; 
   }
   return beta;
}

}