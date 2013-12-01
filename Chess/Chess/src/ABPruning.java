package TicTacToe;

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

  public void pruning(Board board){
      
      System.out.println("Oprettet et AB board objekt... ");
      System.out.println("Printer objektet: ");
      board.printBoard();
      System.out.println("Færdig med at printe");
      miniMax(9, Board.player, board);     
  
  }
  
  public int miniMax(int depth, int turn, Board board)
	{  
            Stack moves = new Stack();
            if (moves.empty()){
                  switch (board.getWinner()){
                   case 1:  return inf;
                   case 2:  return -inf;
                   case 3:  return 0;                    
                }
            }
            else{
                for (Object move : moves) {
                best_score = inf;                
                Board newBoard = new Board();
                newBoard = board;
                moves.add(move);                
                if (turn == 1){                    
                    score = alphaBetaMax(alpha, beta, depth);                               
                    }
                else{
                    score = alphaBetaMin(alpha, beta, depth);                               
                }               
            }
                
               /* 
                 * apply_move(new_board, moves_list[i]);
                minimax(new_board, depth+1, max_depth, the_score, the_move);
                if (better(the_score, best_score)) then
                    best_score = the_score;
                    best_move = the_move;
                endif
            enddo
            chosen_score = best_score;
            chosen_move = best_move;
        endif
                
                */                
        
    }       
            return score;
 }
                           
public int alphaBetaMax( int alpha, int beta, int depth) {
   Stack moves = new Stack();
    for (Object move : moves) {
      score = alphaBetaMin( alpha, beta, depth - 1);
      if( score >= beta )
         return beta;
      if( score > alpha )
         alpha = score; 
   }
   return alpha;
}
 
public int alphaBetaMin( int alpha, int beta, int depth) {  
   Stack moves = new Stack();
    for (Object move : moves) {   
       score = alphaBetaMax( alpha, beta, depth - 1);
      if( score <= alpha )
         return alpha;
      if( score < beta )
         beta = score; 
   }
   return beta;
}
        
    private int evaluate(Board board) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

